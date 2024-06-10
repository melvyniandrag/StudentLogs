package com.ladescoberta.studentlogs.datatypes

val serviceTypeCodeMap : Map<String, String> = mapOf(
    "EV" to "Evaluation",
    "AS" to "Assessment",
    "IFSP" to "Meeting",
    "AU" to "Audiology",
    "DI" to "Developmental Intervention",
    "FT" to "Family Training",
    "HS" to "Health Service",
    "MS" to "Medical Service",
    "NU" to "Nursing",
    "NT" to "Nutrition",
    "OT" to "Occupational Therapy",
    "PT" to "Physical Therapy",
    "PSY" to "Psychological",
    "SLP" to "Speech / Language Therapy",
    "SW" to "Social Work",
    "VI" to "Vision",
    "CC" to "Child Care / Respite",
    "I/T" to "Interpreter / Translator",
    "E/S" to "Escort / Security",
    "TPC" to "Transition Planning Conference"
)

val serviceStatusMap: Map<String, String> = mapOf(
    "1" to "Ongoing IFSP Service",
    "2" to "Practitioner Missed / Cancelled (inclement weather related)",
    "3" to "Family Missed / Cancelled (inclement weather related",
    "4" to "Make-up Service Provided",
    "5" to "Compensatory Service Provided"
)

val serviceLocationMap: Map<String, String> = mapOf(
    "1" to "Home",
    "2" to "Inclusive Community EC Program",
    "3" to "Inclusive EIP EC Program",
    "4" to "EC Program - Children with Disabilities",
    "5" to "Hospital (Inpatient)",
    "6" to "Residential Facility",
    "7" to "Service Provider Clinic / Center / Office"

)