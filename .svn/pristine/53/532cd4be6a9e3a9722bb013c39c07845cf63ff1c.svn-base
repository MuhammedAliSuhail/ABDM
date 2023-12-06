package com.safecare.abdm.wellnessRecord;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Composition.CompositionStatus;
import org.hl7.fhir.r4.model.Composition.SectionComponent;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safecare.abdm.model.OrganizationModel;
import com.safecare.abdm.model.PatientModel;
import com.safecare.abdm.model.PractitionerModel;
import com.safecare.abdm.model.VitalSignsModel;
import com.safecare.abdm.model.WomenHealthModel;
import com.safecare.abdm.repository.OrganizationRepo;
import com.safecare.abdm.repository.PatientRepo;
import com.safecare.abdm.repository.PractitionerRepo;
import com.safecare.abdm.repository.VitalSignsRepo;
import com.safecare.abdm.repository.WomenHealthRepo;
import com.safecare.abdm.resource.ResourcePopulator;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.validation.FhirValidator;

@Service
public class WellnessRecord {
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	PractitionerRepo practitionerRepo;
	@Autowired
	OrganizationRepo organizationRepo;
	@Autowired
	VitalSignsRepo vitalSignsRepo;
	@Autowired
	WomenHealthRepo womenHealthRepo;

//	@Autowired
//	ResourcePopulator resourcePopulator;
	static FhirContext ctx = FhirContext.forR4();
	static FhirInstanceValidator instanceValidator;
	static FhirValidator validator;

	public String wellnessRecord(String visitId, int encounterType) {
		PatientModel patientModel = patientRepo.getPatient(visitId, encounterType);
		PractitionerModel practitionerModel = practitionerRepo.getPractitioner(visitId, encounterType);
		OrganizationModel organizationModel = organizationRepo.getOrganization();
		VitalSignsModel vitalSignsModel = vitalSignsRepo.getVitalSings(visitId);
		WomenHealthModel womenHealthModel = womenHealthRepo.getWomenHealth(visitId);

		Date today = new Date();
		Bundle WellnessRecordBundle = new Bundle();

		// Set logical id of this artifact
		WellnessRecordBundle.setId("WellnessRecord-visitNo-" + visitId);

		// Set metadata about the resource - Version Id, Lastupdated Date, Profile
		Meta meta = WellnessRecordBundle.getMeta();
		meta.setVersionId("1");
		meta.setLastUpdatedElement(new InstantType(new Date()));
		meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle");

		// Set Confidentiality as defined by affinity domain
		meta.addSecurity(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-Confidentiality", "V", "very restricted"));

		// Set version-independent identifier for the Composition
		Identifier identifier = WellnessRecordBundle.getIdentifier();
		identifier.setSystem("http://hip.in");
		identifier.setValue("305fecc2-4ba2-46cc-9ccd-efa755aff51d");

		// Set Bundle Type
		WellnessRecordBundle.setType(BundleType.DOCUMENT);

		// Set Timestamp
		WellnessRecordBundle.setTimestampElement(new InstantType(today));
		List<BundleEntryComponent> listBundleEntries = WellnessRecordBundle.getEntry();

		BundleEntryComponent bundleEntry1 = new BundleEntryComponent();
		bundleEntry1.setFullUrl("Composition/" + visitId);
		bundleEntry1.setResource(populateWellnessRecordCompositionResource(visitId, patientModel, practitionerModel,
				vitalSignsModel, womenHealthModel));
		BundleEntryComponent bundleEntry2 = new BundleEntryComponent();
		bundleEntry2.setFullUrl("Practitioner/" + practitionerModel.getLicenseCode());
		bundleEntry2.setResource(ResourcePopulator.populatePractitionerResource(practitionerModel));

		BundleEntryComponent bundleEntry3 = new BundleEntryComponent();
		bundleEntry3.setFullUrl("Patient/" + patientModel.getPatientId());
		bundleEntry3.setResource(ResourcePopulator.populatePatientResource(patientModel));

		BundleEntryComponent bundleEntry4 = new BundleEntryComponent();
		bundleEntry4.setFullUrl("Observation/respiratory-rate");
		bundleEntry4.setResource(ResourcePopulator.populateRespitaryRateResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry5 = new BundleEntryComponent();
		bundleEntry5.setFullUrl("Observation/heart-rate");
		bundleEntry5.setResource(ResourcePopulator.populateHeartRateResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry25 = new BundleEntryComponent();
		bundleEntry25.setFullUrl("Observation/oxygen-saturation");
		bundleEntry25.setResource(ResourcePopulator.populateOxygenSaturationResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry6 = new BundleEntryComponent();
		bundleEntry6.setFullUrl("Observation/body-temperature");
		bundleEntry6.setResource(ResourcePopulator.populateBodyTemperatureResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry7 = new BundleEntryComponent();
		bundleEntry7.setFullUrl("Observation/body-height");
		bundleEntry7.setResource(ResourcePopulator.populateBodyHeightResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry8 = new BundleEntryComponent();
		bundleEntry8.setFullUrl("Observation/body-weight");
		bundleEntry8.setResource(ResourcePopulator.populateBodyWeightResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry9 = new BundleEntryComponent();
		bundleEntry9.setFullUrl("Observation/bmi");
		bundleEntry9.setResource(ResourcePopulator.populateBMIResource(vitalSignsModel, patientModel));

		BundleEntryComponent bundleEntry10 = new BundleEntryComponent();
		bundleEntry10.setFullUrl("Observation/blood-pressure");
		bundleEntry10.setResource(
				ResourcePopulator.populateBloodPressureResource(vitalSignsModel, patientModel, practitionerModel));
		listBundleEntries.add(bundleEntry1);
		listBundleEntries.add(bundleEntry2);
		listBundleEntries.add(bundleEntry3);
		listBundleEntries.add(bundleEntry4);
		listBundleEntries.add(bundleEntry5);
		listBundleEntries.add(bundleEntry25);
		listBundleEntries.add(bundleEntry6);
		listBundleEntries.add(bundleEntry7);

		listBundleEntries.add(bundleEntry8);
		listBundleEntries.add(bundleEntry9);
		listBundleEntries.add(bundleEntry10);
		IParser parser;
		parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);

		// Serialize populated bundle
		String serializeBundle = parser.encodeResourceToString(WellnessRecordBundle);
		return serializeBundle;
	}

	static Composition populateWellnessRecordCompositionResource(String visitId, PatientModel patientModel,
			PractitionerModel practitionerModel, VitalSignsModel vitalSignsModel, WomenHealthModel womenHealthModel) {
		Composition composition = new Composition();

		// Set logical id of this artifact
		composition.setId("Composition/" + visitId);

		// Set metadata about the resource - Version Id, Lastupdated Date, Profile
		Meta meta = composition.getMeta();
		meta.setVersionId("1");
		meta.setLastUpdatedElement(new InstantType(new Date()));
		meta.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/WellnessRecord");

		// Set language of the resource content
		composition.setLanguage("en-IN");

		// Plain text representation of the concept
		Narrative text = composition.getText();
		text.setStatus(NarrativeStatus.GENERATED);
		text.setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><h4>Narrative with Details</h4><p>This is a Wellness Record of Patient ABC. Generated Summary: id: 1; Medical Record Number = 1234 (System : {https://healthid.ndhm.gov.in}); active; ABC ; ph: +919818512600(HOME); gender: female; birthDate: 1981-01-12</p><p><b>Observation Details:</b><table><tr><td><b>Observation</b></td><td><b>Value</b></td></tr><tr><td>Respiratory rate</td><td>26 breaths/minute</td></tr><tr><td>Heart rate</td><td>44 beats/minute</td></tr><tr><td>Oxygen saturation in Arterial blood</td><td>99 %</td></tr><tr><td>Body surface temperature</td><td>36.5 Cel</td></tr><tr><td>Body height</td><td>66.899999999999991 in</td></tr><tr><td>Body Weight</td><td>185 lbs</td></tr><tr><td>Body mass index (BMI) [Ratio]</td><td>16.2 kg/m2</td></tr><tr><td>Blood pressure panel with all children optional</td><td><ul><li>Systolic blood pressure : 107 mmHg</li><li>Diastolic blood pressure : 60 mmHg</li></ul></td></tr><tr><td>Number of steps in unspecified time Pedometer</td><td>10000 steps</td></tr><tr><td>Calories burned</td><td>800 kcal</td></tr><tr><td>Sleep duration</td><td>8 h</td></tr><tr><td>Body fat [Mass] Calculated</td><td>11 kg</td></tr><tr><td>Glucose [Mass/volume] in Blood</td><td>142 mg/dL</td></tr><tr><td>Fluid intake oral Estimated</td><td>3 Litres</td></tr><tr><td>Calorie intake total</td><td>1750 kcal</td></tr><tr><td>Age at menarche</td><td>14 age</td></tr><tr><td>Last menstrual period start date</td><td>110120 MMDDYY</td></tr><tr><td>Diet [Type]</td><td>Vegan</td></tr><tr><td>Finding of tobacco smoking behavior</td><td>Never smoked tobacco</td></tr></table></p></div>");

		// Set version-independent identifier for the Composition
		Identifier identifier = composition.getIdentifier();
		identifier.setSystem("https://ndhm.in/phr");
		identifier.setValue("645bb0c3-ff7e-4123-bef5-3852a4784813");

		// Status can be preliminary | final | amended | entered-in-error
		composition.setStatus(CompositionStatus.FINAL);

		// Kind of composition ("Wellness record")
		composition.getType().setText("Wellness Record");

		// Set subject - Who and/or what the composition/Wellness record is about
		composition.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId())
				.setDisplay(patientModel.getPatientName()));

		// Set Timestamp
		composition.setDateElement(new DateTimeType(new Date()));

		// Set author - Who and/or what authored the composition/Wellness record
		composition.addAuthor(new Reference().setReference("Practitioner/" + practitionerModel.getLicenseCode())
				.setDisplay(practitionerModel.getPractitionerName()));

		// Set a Human Readable name/title
		composition.setTitle("Wellness Record");

		SectionComponent section1 = new SectionComponent();
		if (vitalSignsModel != null) {
			section1.setTitle("Vital Signs");
			section1.addEntry(new Reference().setReference("Observation/respiratory-rate"))
					.addEntry(new Reference().setReference("Observation/heart-rate"))
					.addEntry(new Reference().setReference("Observation/oxygen-saturation"))
					.addEntry(new Reference().setReference("Observation/body-temperature"))
					.addEntry(new Reference().setReference("Observation/blood-pressure"));
		}

		SectionComponent section2 = new SectionComponent();
		if (vitalSignsModel != null) {
			section2.setTitle("Body Measurement");
			section2.addEntry(new Reference().setReference("Observation/body-height"))
					.addEntry(new Reference().setReference("Observation/body-weight"))
					.addEntry(new Reference().setReference("Observation/bmi"));
		}

		SectionComponent section5 = new SectionComponent();
		if (womenHealthModel != null) {
			section5.setTitle("Women Health");
			section5.addEntry(new Reference().setReference("Observation/AgeOfMenarche"))
					.addEntry(new Reference().setReference("Observation/LastMenstrualPeriod"));

		}

		SectionComponent section7 = new SectionComponent();
		section7.setTitle("Document Reference");
		section7.addEntry(new Reference().setReference("DocumentReference/DocumentReference-01"));

		composition.addSection(section1);
		composition.addSection(section2);

		composition.addSection(section5);

		composition.addSection(section7);

		return composition;

	}
}
