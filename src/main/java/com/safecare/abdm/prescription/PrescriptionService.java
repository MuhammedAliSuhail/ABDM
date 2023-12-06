package com.safecare.abdm.prescription;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Composition.CompositionStatus;
import org.hl7.fhir.r4.model.Composition.SectionComponent;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestIntent;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestStatus;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safecare.abdm.notify.DateRange;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

@Service
public class PrescriptionService {
	@Autowired
	PrescriptionRepo prescriptionRepo;
	static FhirContext ctx = FhirContext.forR4();

	public String prescriptionBundle(String visitId, DateRange dateRange) throws ParseException {

		PatientDetail patientDetail = prescriptionRepo.getPatientDetailsFromVisitId(visitId);
		DoctorDetail doctorDetail = prescriptionRepo.getDoctorDetailsByVisitId(visitId);
		List<PrescriptionDetail> prescriptionDetails = prescriptionRepo.getPrecriptionList(visitId, dateRange);
		List<DiagnosisDetail> diagnosisDetails = prescriptionRepo.getDiagnosisDetails(visitId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(doctorDetail.getCreateDate()));

		Bundle prescriptionBundle = new Bundle();

		// Set logical id of this artifact
		prescriptionBundle.setId("prescription-bundle-" + visitId);

		// Set metadata about the resource
		Meta meta = prescriptionBundle.getMeta();
		meta.setVersionId("1");
		meta.setLastUpdatedElement(new InstantType(calendar));
		meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle");

		// Set Confidentiality as defined by affinity domain
		meta.addSecurity(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-Confidentiality", "V", "very restricted"));

		// Set version-independent identifier for the Bundle
		Identifier identifier = prescriptionBundle.getIdentifier();
		identifier.setValue("OP_PRESCIPTION_VISIT_" + visitId);
		identifier.setSystem("http://hip.in");

		// Set Bundle Type
		prescriptionBundle.setType(BundleType.DOCUMENT);

		// Set Timestamp
		prescriptionBundle.setTimestampElement(new InstantType(calendar));

		// Add resources entries for bundle with Full URL
		List<BundleEntryComponent> listBundleEntries = prescriptionBundle.getEntry();

		BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
		bundleEntry1.setFullUrl("Composition/Composition-" + visitId);
		bundleEntry1.setResource(
				populatePrescriptionCompositionResource(patientDetail, doctorDetail, prescriptionDetails, visitId));

		BundleEntryComponent bundleEntry2 = new BundleEntryComponent();
		bundleEntry2.setFullUrl("Patient/" + patientDetail.getUhid());
		bundleEntry2.setResource(populatePatientResource(patientDetail, doctorDetail));

		BundleEntryComponent bundleEntry3 = new BundleEntryComponent();
		bundleEntry3.setFullUrl("Practitioner/" + doctorDetail.getDoctorCode());
		bundleEntry3.setResource(populatePractitionerResource(doctorDetail));
		listBundleEntries.add(bundleEntry1);
		listBundleEntries.add(bundleEntry2);
		listBundleEntries.add(bundleEntry3);
//		BundleEntryComponent bundleEntry4 = new BundleEntryComponent();
//		bundleEntry4.setFullUrl("MedicationRequest/MedicationRequest-01");
//		bundleEntry4.setResource(ResourcePopulator.populateMedicationRequestResource());
		for (PrescriptionDetail prescriptionDetail : prescriptionDetails) {
			BundleEntryComponent bundleEntry5 = new BundleEntryComponent();
			bundleEntry5.setFullUrl("MedicationRequest/" + prescriptionDetail.getPrescriptionId());
			bundleEntry5.setResource(
					populateSecondMedicationRequestResource(patientDetail, prescriptionDetail, doctorDetail));
			listBundleEntries.add(bundleEntry5);
		}
		for (DiagnosisDetail diagnosisDetail : diagnosisDetails) {
			BundleEntryComponent bundleEntry6 = new BundleEntryComponent();
			bundleEntry6.setFullUrl("Condition/" + diagnosisDetail.getDiagMasterID());
			bundleEntry6.setResource(populateConditionResource(patientDetail, diagnosisDetail));
			listBundleEntries.add(bundleEntry6);
		}

		BundleEntryComponent bundleEntry7 = new BundleEntryComponent();
		bundleEntry7.setFullUrl("Binary/Binary-01");
		bundleEntry7.setResource(ResourcePopulator.populateBinaryResource());

		listBundleEntries.add(bundleEntry7);
		IParser parser;
		parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);

		// Serialize populated bundle
		String serializeBundle = parser.encodeResourceToString(prescriptionBundle);
		return serializeBundle;
	}

	private Composition populatePrescriptionCompositionResource(PatientDetail patientDetail, DoctorDetail doctorDetail,
			List<PrescriptionDetail> prescriptionDetails, String visitId) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(doctorDetail.getCreateDate()));
		Composition composition = new Composition();

		// Set logical id of this artifact
		composition.setId("Composition-" + visitId);

		// Set metadata about the resource - Version Id, Lastupdated Date, Profile
		Meta meta = composition.getMeta();
		meta.setVersionId("1");
		meta.setLastUpdatedElement(new InstantType(calendar));
		meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/PrescriptionRecord");

		// Set language of the resource content
		composition.setLanguage("en-IN");

		// Plain text representation of the concept
		Narrative text = composition.getText();
		text.setStatus((NarrativeStatus.GENERATED));
		text.setDivAsString("<div xmlns=\"http://www.w3.org/1999/xhtml\">Prescription report</div>");

		// Set version-independent identifier for the Composition
		Identifier identifier = composition.getIdentifier();
		identifier.setSystem("https://ndhm.in/phr");
		identifier.setValue("645bb0c3-ff7e-4123-bef5-3852a4784813");

		// Status can be preliminary | final | amended | entered-in-error
		composition.setStatus(CompositionStatus.FINAL);

		// Kind of composition ("Prescription record ")
		composition
				.setType(new CodeableConcept(new Coding("http://snomed.info/sct", "440545006", "Prescription record")));

		// Set subject - Who and/or what the composition/Prescription record is about
		composition.setSubject(new Reference().setReference("Patient/" + patientDetail.getUhid()));

		// Set Timestamp
		composition.setDateElement(new DateTimeType(calendar));

		// Set author - Who and/or what authored the composition/Presciption record
		composition.addAuthor(new Reference().setReference("Practitioner/" + doctorDetail.getDoctorCode()));

		// Set a Human Readable name/title
		composition.setTitle("Prescription record");

		// Composition is broken into sections / Prescription record contains single
		// section to define the relevant medication requests
		// Entry is a reference to data that supports this section
		List<Reference> references = new ArrayList<>();
		for (PrescriptionDetail prescriptionDetail : prescriptionDetails) {
			Reference reference1 = new Reference();
			reference1.setReference("MedicationRequest/" + prescriptionDetail.getPrescriptionId());
			reference1.setType("MedicationRequest");
			references.add(reference1);

		}

		SectionComponent section = new SectionComponent();
		section.setTitle("Prescription record");
		section.setCode(new CodeableConcept(new Coding("http://snomed.info/sct", "440545006", "Prescription record")));
		for (Reference reference : references) {
			section.addEntry(reference);
		}

		composition.addSection(section);

		return composition;
	}

	public static Patient populatePatientResource(PatientDetail patientDetail, DoctorDetail doctorDetail)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(doctorDetail.getCreateDate()));
		Patient patient = new Patient();
		patient.setId(patientDetail.getUhid());
		patient.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(calendar))
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient");
		patient.getText().setStatus(NarrativeStatus.GENERATED)
				.setDivAsString("<div xmlns=\"http://www.w3.org/1999/xhtml\">" + patientDetail.getPatientName() + ","
						+ patientDetail.getAge() + "," + patientDetail.getGender() + "</div>");
		patient.addIdentifier()
				.setType(new CodeableConcept(
						new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MR", "Medical record number")))
				.setSystem("https://ndhm.in/SwasthID").setValue(patientDetail.getUhid());
		patient.addName().setText(patientDetail.getPatientName());
		patient.addTelecom().setSystem(ContactPointSystem.PHONE).setValue(patientDetail.getPhoneNo())
				.setUse(ContactPointUse.HOME);
		patient.setGender(AdministrativeGender.fromCode(patientDetail.getGender().toLowerCase()))
				.setBirthDateElement(new DateType(patientDetail.getDateOfBirth()));
		return patient;
	}

	public static Practitioner populatePractitionerResource(DoctorDetail doctorDetail) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(doctorDetail.getCreateDate()));
		Practitioner practitioner = new Practitioner();
		practitioner.setId(doctorDetail.getDoctorCode());
		practitioner.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(calendar))
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner");
		practitioner.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\">" + doctorDetail.getDoctorName() + "</div>");
		practitioner.addIdentifier()
				.setType(new CodeableConcept(
						new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MD", "Medical License number")))
				.setSystem("https://ndhm.in/DigiDoc").setValue(doctorDetail.getLicenseId());
		practitioner.addName().setText(doctorDetail.getDoctorName());
		return practitioner;
	}

	public static MedicationRequest populateSecondMedicationRequestResource(PatientDetail patientDetail,
			PrescriptionDetail prescriptionDetail, DoctorDetail doctorDetail) {
		MedicationRequest medicationRequest = new MedicationRequest();
		medicationRequest.setId(prescriptionDetail.getPrescriptionId() + "");
		medicationRequest.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest");
		medicationRequest.setStatus(MedicationRequestStatus.ACTIVE);
		medicationRequest.setIntent(MedicationRequestIntent.ORDER);
		medicationRequest.setMedication(new CodeableConcept().setText(prescriptionDetail.getProductName()));
		medicationRequest.setSubject(new Reference().setReference("Patient/" + patientDetail.getUhid())
				.setDisplay(patientDetail.getPatientName()));
		medicationRequest.setAuthoredOnElement(new DateTimeType(prescriptionDetail.getCreatedDate()));
		medicationRequest.setRequester(new Reference().setReference("Practitioner/" + doctorDetail.getDoctorCode())
				.setDisplay(doctorDetail.getDoctorName()));
		medicationRequest.getReasonCode()
				.add(new CodeableConcept(new Coding("http://snomed.info/sct", "602001", "Ross river fever")));
		medicationRequest.getReasonReference().add(new Reference().setReference("Condition/Condition-01"));
		medicationRequest.addDosageInstruction(new Dosage().setText(prescriptionDetail.getInstruction()));
		return medicationRequest;
	}

	public static Condition populateConditionResource(PatientDetail patientDetail, DiagnosisDetail diagnosisDetail) {
		Condition condition = new Condition();
		condition.setId(diagnosisDetail.getDiagMasterID());
		condition.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition");
		condition.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\">" + diagnosisDetail.getDiagnosisName() + "</div>");
		condition.setSubject(new Reference().setReference("Patient/" + patientDetail.getUhid()));
		condition.getCode().addCoding(
				new Coding("http://snomed.info/sct", diagnosisDetail.getCptCode(), diagnosisDetail.getDiagnosisName()))
				.setText(diagnosisDetail.getDiagnosisName());
		return condition;
	}
}
