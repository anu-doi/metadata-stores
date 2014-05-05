INSERT INTO FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) VALUES ('11','1117','111706','Epidemiology');
INSERT INTO FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) VALUES ('11','1117','111714','Mental Health');
INSERT INTO FOR_codes (chrForDivisionCode, chrForGroupCode, chrForObjectiveCode, chrForDescription) VALUES ('11','1103','110311','Medical Genetics (excl. Cancer Genetics)');

INSERT INTO useraccounts (intSystemCounter, chrStaffNumber, chrTitle, chrFirstname, chrSurname, chrFax, chrTelephone, chrEmail, chrForcode1, chrForpercentage1, chrForcode2, chrForpercentage2, chrForcode3, chrForpercentage3) VALUES (1, 't1234567','Dr', 'Samuel','Roderick', '6215 1112', '6215 1111', 'samuel.roderick@anu.edu.au', '111706','60', '111714','30','110311','10');

INSERT INTO externalUsers (chrCode, chrFirstname, chrSurname, chrCountry, chrInstitutionName) VALUES ('e1234', 'Homer', 'Simpson', 'America', 'University of Doh');

INSERT INTO contracts_grants_main (chrContractCode, chrShortTitle, chrPrimaryFundsProvider, chrSchemeRef, chrGrantStartDate, chrCompletionDate, chrStatus, chrFORcode1, chrFORpercentage1, chrFORcode2, chrFORpercentage2, chrFORcode3, chrFORpercentage3) VALUES ('CON12345','Investigation into Test Methods','Australian Research Council (ARC)','DP1000000','2010-07-01 00:00:00.0','2012-04-01 00:00:00.0', 'COMPLETED','111706','50','111714','30','110311','20');
INSERT INTO contracts_grants_investigators (chrContractInvestigatorCode, chrContractCode, chrStaffNumber, chrRoute, chrPrimary) VALUES ('CON12345xxt1234567x', 'CON12345', 't1234567', null, 'Yes');

INSERT INTO research_outputs_level_2 (chrOutput3Code, chrOutput2Description) VALUES ('C1', 'C1: journal article meeting HERDC requirements');
INSERT INTO research_outputs_journals (intJournalCode, chrJournalName, chrYear, chrISSN) VALUES (1,'Journal of Everything','2012','1234-5678');
--INSERT INTO research_outputs_data1 (chrOutput6code, chrReportingYear, intConferenceCode, intJournalCode, intBookCode, chrOutput3Code, chrPublicationTitle, chrFirstNamedAuthor, chrFORcode1, chrFORpercentage1, chrFORcode2, chrFORpercentage2, chrFORcode3, chrFORpercentage3, chrISSN, chrISBN) VALUES ('f1234xPUB100', '2012',null,1,null,'C1', 'Testing Methods', 't1234567', '111706','50','111714','30','110311','20','1234-5678',null);
INSERT INTO research_outputs_data1 (chrOutput6code, chrReportingYear, intJournalCode, chrOutput3Code, chrPublicationTitle, chrFirstNamedAuthor, chrFORcode1, chrFORpercentage1, chrFORcode2, chrFORpercentage2, chrFORcode3, chrFORpercentage3, chrISSN) VALUES ('f1234xPUB100', '2012',1,'C1', 'Testing Methods', 't1234567', '111706','50','111714','30','110311','20','1234-5678');
INSERT INTO research_outputs_data_authors (chrOutput6code, chrOutputInvestigatorCode, chrStatus, chrRoleCode, chrStaffNumber, chrOrder) VALUES ('f1234xPUB100','f1234xPUB100xt1234567',null,null,'t1234567','01');

INSERT INTO research_outputs_level_2 (chrOutput3Code, chrOutput2Description) VALUES ('B1', 'B1: chapter meeting HERDC requirements');
INSERT INTO research_outputs_books (intBookCode, chrBookName, chrYear, chrISBN) VALUES (1,'Methodologies for Unit Testing','2012','978-1234-5678');
INSERT INTO research_outputs_data1 (chrOutput6code, chrReportingYear, intBookCode, chrOutput3Code, chrPublicationTitle, chrFirstNamedAuthor, chrFORcode1, chrFORpercentage1, chrFORcode2, chrFORpercentage2, chrFORcode3, chrFORpercentage3, chrISBN) VALUES ('f1234xPUB101', '2012',1,'B1', 'Test Driven Development', 't1234567', '111706','40','111714','35','110311','25','978-1234-5678');
INSERT INTO research_outputs_data_authors (chrOutput6code, chrOutputInvestigatorCode, chrStatus, chrRoleCode, chrStaffNumber, chrOrder) VALUES ('f1234xPUB101','f1234xPUB101xt1234567',null,null,'t1234567','01');

INSERT INTO research_outputs_level_2 (chrOutput3Code, chrOutput2Description) VALUES ('E1', 'E1: conference paper meeting HERDC requirements');
INSERT INTO research_outputs_conferences (intConferenceCode, chrConferenceName, chrYear, chrISBN) VALUES (1,'16th Annual Conference of Software Engineers','2011','978-1234-5679');
INSERT INTO research_outputs_data1 (chrOutput6code, chrReportingYear, intConferenceCode, chrOutput3Code, chrPublicationTitle, chrFirstNamedAuthor, chrFORcode1, chrFORpercentage1, chrFORcode2, chrFORpercentage2, chrFORcode3, chrFORpercentage3, chrISBN) VALUES ('f1234xPUB102', '2011',1,'E1', 'Examining Software Testing', 't1234567', '111706','70','111714','15','110311','15','1234-5678');
INSERT INTO research_outputs_data_authors (chrOutput6code, chrOutputInvestigatorCode, chrStatus, chrRoleCode, chrStaffNumber, chrOrder) VALUES ('f1234xPUB102','f1234xPUB102xt1234567',null,null,'t1234567','01');

INSERT INTO facultyschoolcentre (chrTierCode, chrTier2name, chrTier2code, chrDateStatus, chrAou, chrFinanceCode, chrHraou) VALUES ('IO','10', 'School of Something', '2012','12','123','124');
INSERT INTO departments(chrTier3name, chrTier1code, chrTier2code, chrTier3code, chrCollegeCode, chrSchoolCode) VALUES ('School of Testing','100','10','200','TE','IO');
INSERT INTO contracts_grants_departments (chrPrimary, chrDepartmentCode, chrContractDepartmentCode, chrContractCode) VALUES ('Yes','100','CON12345x100','CON12345');
