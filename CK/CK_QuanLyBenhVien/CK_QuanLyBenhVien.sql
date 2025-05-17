INSERT INTO Department (id, location, name) VALUES ('Dept01', 'Building A', 'Cardiology')
INSERT INTO Department (id, location, name) VALUES ('Dept02', 'Building B', 'Dermatology')
INSERT INTO Department (id, location, name) VALUES ('Dept03', 'Building C', 'Neurology')
INSERT INTO Department (id, location, name) VALUES ('Dept04', 'Building D', 'Pediatrics')
INSERT INTO Department (id, location, name) VALUES ('Dept05', 'Building E', 'Orthopedics')



INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D001', 'Dr. Alice Brown', '555-2000', 'Cardiology')
INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D002', 'Dr. Bob White', '555-2001', 'Dermatology')
INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D003', 'Dr. Clara Blue', '555-2002', 'Neurology')
INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D004', 'Dr. David Green', '555-2003', 'Pediatrics')
INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D005', 'Dr. Erica Stone', '555-2004', 'Orthopedics')
INSERT INTO Doctor (id, name, phone, speciality) VALUES ('D006', 'Dr. Hiro', '111-2004', 'Dermatology')


INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('001-00-0001', 'John Doe', '555-1000', '123 Elm Street', '1980-01-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('002-00-0002', 'Jane Doe', '555-1001', '124 Elm Street', '1981-02-01', 'Female')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('003-00-0003', 'Jim Beam', '555-1002', '125 Elm Street', '1982-03-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('004-00-0004', 'Jill Hill', '555-1003', '126 Elm Street', '1983-04-01', 'Female')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('005-00-0005', 'Jack Black', '555-1004', '127 Elm Street', '1984-05-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('001-00-0005', 'Jonh Kill', '555-1114', 'New USA Street', '1974-05-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('002-00-0005', 'Ques kll', '111-1114', '12 net guh Street', '1972-05-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('006-00-0105', 'Nguyen Lan', '113333', 'hcm Street', '1972-12-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('022-00-0105', 'Ha', '111-17777', '12 nvb Street', '1974-04-01', 'Male')
INSERT INTO Patient (id, name, phone, address, dateOfBirth, gender) VALUES ('072-00-0105', 'Hanh PL', '11331-17777', '44 nguyen tri phuong Street', '2003-04-01', 'Male')



INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES ('2023-04-01', 'Cardiac Arrhythmia', '2023-04-10', '001-00-0001', 'Dept01', 'D001')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES ('2023-10-02', 'Acne', '2023-04-12', '002-00-0002', 'Dept02', 'D002')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES('2023-03-03', 'Migraine', '2023-04-13', '003-00-0003', 'Dept03', 'D003')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES ('2023-02-04', 'Bronchitis', '2023-04-14', '004-00-0004', 'Dept04', 'D004')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES('2022-04-05', 'Fractured Arm', '2023-04-15', '005-00-0005', 'Dept05', 'D005')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES('2021-04-06', 'Fractured', '2021-04-06', '001-00-0005', 'Dept02', 'D006')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES('2021-04-07', 'Fractured', '2021-04-08', '002-00-0005', 'Dept02', 'D006')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES ('2023-04-23', 'Cardiac Arrhythmia', '2023-06-10', '006-00-0105', 'Dept01', 'D001')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES ('2023-04-26', 'Cardiac Arrhythmia', '2023-07-10', '022-00-0105', 'Dept01', 'D001')
INSERT INTO Treatment (startDate, diagnosis, endDate, patient_id, department_id, doctor_id) VALUES('2023-04-03', 'Migraine', '2023-04-03', '072-00-0105', 'Dept03', 'D003')


select * from [dbo].[Patient]
select * from [dbo].[Department]
select * from [dbo].[Doctor]
select * from [dbo].[Treatment]

-- select do.*
-- from [dbo].[Treatment] t join [dbo].[Doctor] do on t.doctor_id = do.id  
-- join [dbo].[Department] d on t.department_id = d.id 
--where d.name = 'Dermatology'


SELECT YEAR(T.startDate) AS TreatmentYear, MONTH(T.startDate) AS TreatmentMonth, count(*) tongsoca
FROM [dbo].[Treatment] T
GROUP BY  YEAR(T.startDate) , MONTH(T.startDate)

select do.*
 from [dbo].[Treatment] T join [dbo].[Doctor] do on T.doctor_id = do.id  
 join [dbo].[Department] d on t.department_id = d.id 
where  YEAR(T.startDate) = '2021' and MONTH(T.startDate) = '4'
ORDER BY do.speciality




Select do.* 
from Treatment t
join Department de
on t.department_id = de.id
join Doctor do
on t.doctor_id = do.id
where de.name='Dermatology'


Select do.*,count(*) as tong
from Treatment t
join Doctor do
on t.doctor_id = do.id
where year(t.startDate)=2023 and month(t.startDate)=4
group by do.id, do.name, do.phone, do.speciality






