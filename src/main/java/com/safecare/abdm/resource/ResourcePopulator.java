package com.safecare.abdm.resource;

import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationComponentComponent;
import org.hl7.fhir.r4.model.Observation.ObservationReferenceRangeComponent;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;

import com.safecare.abdm.model.PatientModel;
import com.safecare.abdm.model.PractitionerModel;
import com.safecare.abdm.model.VitalSignsModel;

public class ResourcePopulator {
	public static Patient populatePatientResource(PatientModel patientModel) {
		Patient patient = new Patient();
		patient.setId(patientModel.getPatientId());
		patient.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(new Date()))
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient");
		patient.getText().setStatus(NarrativeStatus.GENERATED)
				.setDivAsString("<div xmlns=\"http://www.w3.org/1999/xhtml\">" + patientModel.getPatientName() + ",Age:"
						+ patientModel.getAge() + ",Gender:" + patientModel.getGender() + "</div>");
		patient.addIdentifier()
				.setType(new CodeableConcept(
						new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MR", "Medical record number")))
				.setSystem("https://ndhm.in/SwasthID").setValue(patientModel.getPatientId());
		patient.addName().setText(patientModel.getPatientName());
		patient.addTelecom().setSystem(ContactPointSystem.PHONE).setValue(patientModel.getContactNumber())
				.setUse(ContactPointUse.HOME);

		patient.setGender(AdministrativeGender.fromCode(patientModel.getGender()))
				.setBirthDateElement(new DateType(patientModel.getDob()));
		return patient;
	}

	// Populate Practitioner Resource
	public static Practitioner populatePractitionerResource(PractitionerModel practitionerModel) {
		Practitioner practitioner = new Practitioner();
		practitioner.setId(practitionerModel.getLicenseCode());
		practitioner.getMeta().setVersionId("1").setLastUpdatedElement(new InstantType(new Date()))
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner");
		practitioner.getText().setStatus(NarrativeStatus.GENERATED).setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\">" + practitionerModel.getPractitionerName() + "</div>");
		practitioner.addIdentifier()
				.setType(new CodeableConcept(
						new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MD", "Medical License number")))
				.setSystem("https://ndhm.in/DigiDoc").setValue(practitionerModel.getLicenseCode());
		practitioner.addName().setText(practitionerModel.getPractitionerName());
		return practitioner;
	}

	public static Observation populateRespitaryRateResource(VitalSignsModel vitalSignsModel,
			PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("respiratory-rate");
		observation.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationVitalSigns");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: respiratory-rate</p><p><b>meta</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Respiratory rate <span>(Details : {LOINC code '9279-1' = 'Respiratory rate', given as 'Respiratory rate'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>value</b>: 26 breaths/minute<span> (Details: UCUM code /min = '/min')</span></p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.addCategory().setText("Vital Signs");
		observation.setCode(new CodeableConcept(new Coding("http://loinc.org", "9279-1", "Respiratory rate"))
				.setText("Respiratory rate"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getRepiratoryRate()))
				.setUnit("breaths/minute").setSystem("http://unitsofmeasure.org").setCode("/min"));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));

		return observation;

	}

	// Populate Observation/heart-rate Resource
	public static Observation populateHeartRateResource(VitalSignsModel vitalSignsModel, PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("heart-rate");
		observation.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationVitalSigns");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: heart-rate</p><p><b>meta</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Heart rate <span>(Details : {LOINC code '8867-4' = 'Heart rate', given as 'Heart rate'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>value</b>: 44 beats/minute<span> (Details: UCUM code /min = '/min')</span></p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.addCategory().setText("Vital Signs");
		observation.setCode(
				new CodeableConcept(new Coding("http://loinc.org", "8867-4", "Heart rate")).setText("Heart rate"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getHeartRate()))
				.setUnit("beats/minute").setSystem("http://unitsofmeasure.org").setCode("/min"));

		return observation;
	}

	// Populate Observation/oxygen-saturation Resource
	public static Observation populateOxygenSaturationResource(VitalSignsModel vitalSignsModel,
			PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("oxygen-saturation");
		observation.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationVitalSigns");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: oxygen-saturation</p><p><b>meta</b>: </p><p><b>identifier</b>: o1223435-10</p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Oxygen saturation in Arterial blood <span>(Details : {LOINC code '2708-6' = 'Oxygen saturation in Arterial blood', given as 'Oxygen saturation in Arterial blood'}; )</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020 9:30:10 AM</p><p><b>value</b>: 95 %<span> (Details: UCUM code % = '%')</span></p><p><b>interpretation</b>: Normal (applies to non-numeric results) <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'N' = 'Normal', given as 'Normal'})</span></p><p><b>device</b>: <a>DeviceMetric/example</a></p><h3>ReferenceRanges</h3><table><tr><td>-</td><td><b>Low</b></td><td><b>High</b></td></tr><tr><td>*</td><td>90 %<span> (Details: UCUM code % = '%')</span></td><td>99 %<span> (Details: UCUM code % = '%')</span></td></tr></table></div>");
		observation.addIdentifier(
				new Identifier().setSystem("http://goodcare.org/observation/id").setValue("o1223435-10"));
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.addCategory().setText("Vital Signs");
		observation.setCode(
				new CodeableConcept(new Coding("http://loinc.org", "2708-6", "Oxygen saturation in Arterial blood"))
						.setText("Vital Signs"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getOxygenSaturation()))
				.setUnit("%").setSystem("http://unitsofmeasure.org").setCode("%"));
		observation.addInterpretation(new CodeableConcept(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "N", "Normal"))
				.setText("Normal (applies to non-numeric results)"));
		observation.setDevice(new Reference().setReference("DeviceMetric/example"));
		observation.addReferenceRange(new ObservationReferenceRangeComponent()
				.setLow(new Quantity().setValue(90).setUnit("%").setSystem("http://unitsofmeasure.org").setCode("%"))
				.setHigh(new Quantity().setValue(95).setUnit("%").setSystem("http://unitsofmeasure.org").setCode("%")));

		return observation;
	}

	// Populate "Observation/body-temperature Resource
	public static Observation populateBodyTemperatureResource(VitalSignsModel vitalSignsModel,
			PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("body-temperature");
		observation.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationVitalSigns");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: body-temperature</p><p><b>meta</b>: </p><p><b>status</b>: final</p><p><b>code</b>: Body surface temperature <span>(Details : {LOINC code '61008-9' = 'Body surface temperature', given as 'Body surface temperature'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 2021-03-09</p><p><b>value</b>: 36.5 Cel</p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.setCode(new CodeableConcept(new Coding("http://loinc.org", "61008-9", "Body surface temperature"))
				.setText("Body surface temperature"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getBodyTemparature()))
				.setUnit("degF").setSystem("http://unitsofmeasure.org").setCode("{Cel or degF}"));

		return observation;
	}

	// Populate Observation/body-height Resource
	public static Observation populateBodyHeightResource(VitalSignsModel vitalSignsModel, PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("body-height");
		observation.getMeta()
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: body-height</p><p><b>meta</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Body height <span>(Details : {LOINC code '8302-2' = 'Body height', given as 'Body height'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>value</b>: 66.899999999999991 in<span> (Details: UCUM code [in_i] = 'in_i')</span></p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.addCategory().setText("Vital Signs");
		observation.setCode(
				new CodeableConcept(new Coding("http://loinc.org", "8302-2", "Body height")).setText("Body height"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getHeight())).setUnit("cm")
				.setSystem("http://unitsofmeasure.org").setCode("{[in_us],cm,m}"));

		return observation;
	}

	// Populate Observation/body-weight Resource
	public static Observation populateBodyWeightResource(VitalSignsModel vitalSignsModel, PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("body-weight");
		observation.getMeta()
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: body-weight</p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Body Weight <span>(Details : {LOINC code '29463-7' = 'Body weight', given as 'Body Weight'};)</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>value</b>: 185 lbs<span> (Details: UCUM code [lb_av] = 'lb_av')</span></p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.setCode(
				new CodeableConcept(new Coding("http://loinc.org", "29463-7", "Body weight")).setText("Body weight"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getWeight())).setUnit("kg")
				.setSystem("http://unitsofmeasure.org").setCode("{[lb_av],kg,g}"));

		return observation;
	}

	// Populate Observation/bmi Resource
	public static Observation populateBMIResource(VitalSignsModel vitalSignsModel, PatientModel patientModel) {
		Observation observation = new Observation();
		observation.setId("bmi");
		observation.getMeta()
				.addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: bmi</p><p><b>meta</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: BMI <span>(Details : {LOINC code '39156-5' = 'Body mass index (BMI) [Ratio]', given as 'Body mass index (BMI) [Ratio]'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>value</b>: 16.2 kg/m2<span> (Details: UCUM code kg/m2 = 'kg/m2')</span></p></div>");
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(
				new Coding("http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs"))
				.setText("vital Signs"));
		observation
				.setCode(new CodeableConcept(new Coding("http://loinc.org", "29463-7", "Body mass index (BMI) [Ratio]"))
						.setText("BMI"));
		observation.setSubject(new Reference().setReference("Patient/" + patientModel.getPatientId()));
		observation.setEffective(new DateTimeType(vitalSignsModel.getEnterDate()));
		observation.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getBmi())).setUnit("kg/m2")
				.setSystem("http://unitsofmeasure.org").setCode("kg/m2"));

		return observation;
	}

	// Populate "Observation/blood-pressure" Resource
	public static Observation populateBloodPressureResource(VitalSignsModel vitalSignsModel, PatientModel patient,
			PractitionerModel practitioner) {
		Observation observation = new Observation();
		observation.setId("blood-pressure");
		observation.getMeta().addProfile("https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationVitalSigns");
		observation.getText().setStatus(NarrativeStatus.GENERATED);
		observation.getText().setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative with Details</b></p><p><b>id</b>: blood-pressure</p><p><b>meta</b>: </p><p><b>identifier</b>: urn:uuid:187e0c12-8dd2-67e2-99b2-bf273c878281</p><p><b>basedOn</b>: </p><p><b>status</b>: final</p><p><b>category</b>: Vital Signs <span>(Details : {http://terminology.hl7.org/CodeSystem/observation-category code 'vital-signs' = 'Vital Signs', given as 'Vital Signs'})</span></p><p><b>code</b>: Blood pressure systolic &amp; diastolic <span>(Details : {LOINC code '85354-9' = 'Blood pressure panel with all children optional', given as 'Blood pressure panel with all children optional'})</span></p><p><b>subject</b>: <a>Patient/1</a></p><p><b>effective</b>: 29/09/2020</p><p><b>performer</b>: <a>Practitioner/1</a></p><p><b>interpretation</b>: Below low normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'L' = 'Low', given as 'low'})</span></p><p><b>bodySite</b>: Right arm <span>(Details : {SNOMED CT code '368209003' = 'Right upper arm', given as 'Right arm'})</span></p><blockquote><p><b>component</b></p><p><b>code</b>: Systolic blood pressure <span>(Details : {LOINC code '8480-6' = 'Systolic blood pressure', given as 'Systolic blood pressure'};)</span></p><p><b>value</b>: 107 mmHg<span> (Details: UCUM code mm[Hg] = 'mmHg')</span></p><p><b>interpretation</b>: Normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'N' = 'Normal', given as 'normal'})</span></p></blockquote><blockquote><p><b>component</b></p><p><b>code</b>: Diastolic blood pressure <span>(Details : {LOINC code '8462-4' = 'Diastolic blood pressure', given as 'Diastolic blood pressure'})</span></p><p><b>value</b>: 60 mmHg<span> (Details: UCUM code mm[Hg] = 'mmHg')</span></p><p><b>interpretation</b>: Below low normal <span>(Details : {http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation code 'L' = 'Low', given as 'low'})</span></p></blockquote></div>");
		observation.addIdentifier(new Identifier().setSystem("urn:ietf:rfc:3986")
				.setValue("urn:uuid:187e0c12-8dd2-67e2-99b2-bf273c878281"));
		observation.setStatus(ObservationStatus.FINAL);
		observation.addCategory(new CodeableConcept(new Coding(
				"http://terminology.hl7.org/CodeSystem/observation-category", "vital-signs", "Vital Signs")));
		observation.setCode(new CodeableConcept(
				new Coding("http://loinc.org", "85354-9", "Blood pressure panel with all children optional"))
				.setText("Blood pressure panel with all children optional"));
		observation.setSubject(new Reference().setReference("Patient/" + patient.getPatientId()));
		observation.setEffective(new DateTimeType(new Date()));
		observation.addPerformer(new Reference().setReference("Practitioner/" + practitioner.getLicenseCode()));
		observation.addInterpretation(new CodeableConcept(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "L", "low"))
				.setText("Below low normal"));
		observation.getBodySite().addCoding(new Coding("http://snomed.info/sct", "368209003", "Right arm"));
		List<ObservationComponentComponent> componentList = observation.getComponent();
		ObservationComponentComponent component = new ObservationComponentComponent();
		component.setCode(new CodeableConcept(new Coding("http://loinc.org", "8480-6", "Systolic blood pressure")));
		component.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getSystolicBloodPressure()))
				.setUnit("mmHg").setSystem("http://unitsofmeasure.org").setCode("mm[Hg]"));
		component.addInterpretation(new CodeableConcept(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "N", "normal"))
				.setText("Normal"));

		ObservationComponentComponent component1 = new ObservationComponentComponent();
		component1.setCode(new CodeableConcept(new Coding("http://loinc.org", "8462-4", "Diastolic blood pressure")));
		component1.setValue(new Quantity().setValue(Integer.parseInt(vitalSignsModel.getDiastolicBloodPressure()))
				.setUnit("mmHg").setSystem("http://unitsofmeasure.org").setCode("mm[Hg]"));
		component1.addInterpretation(new CodeableConcept(
				new Coding("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation", "L", "low"))
				.setText("Below low normal"));

		componentList.add(component);
		componentList.add(component1);

		return observation;
	}

}
