-- Customers
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST001', 'Nguyen Van A', '0123456789', 'nva@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST002', 'Tran Thi B', '0987654321', 'ttb@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST003', 'Le Thi C', '0192837465', 'ltc@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST004', 'Pham Van D', '0283746528', 'pvd@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST005', 'Hoang Thi E', '0391827364', 'hte@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST006', 'Nguyen Van F', '0123456781', 'nvf@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST007', 'Tran Van G', '0987654322', 'tvg@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST008', 'Le Thi H', '0192837466', 'lth@gmail.com')
INSERT INTO customers (customer_id, customer_name, phone_number, email) VALUES
('CUST009', 'Ngueyn Lam Ngoc', '0192837465', 'ngoc@gmail.com')
-- Rooms
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM101', 'Standard Room', 'Suite', 'Double Bed', 2, 0, 1000.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM102', 'Deluxe Room', 'Deluxe', 'King Bed', 2, 0, 1500.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM103', 'Suite Room', 'Single', 'Queen Bed', 4, 0, 2000.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM104', 'Family Room', 'Single', 'Double Bed', 4, 0, 1800.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM105', 'Single Room', 'Single', 'Single Bed', 1, 0, 800.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM106', 'Double Room', 'Double', 'Two Single Beds', 2, 0, 1200.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM107', 'Executive Room', 'Executive', 'King Bed', 2, 0, 2200.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM108', 'Presidential Suite', 'Suite', 'King Bed', 4, 0, 5000.00)
INSERT INTO rooms (room_id, description, type, bed_option, sleeps_count, smoking_allowed, price) VALUES
('ROOM109', 'Presidential Single', 'Single', 'Queed Bed', 3, 0, 5000.00)
-- Bookings
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK001', '2024-04-01', '2024-04-03', 500.00, 'No special requests', 'CUST001')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK002', '2024-04-05', '2024-04-07', 700.00, 'Late check-in', 'CUST002')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK003', '2024-04-10', '2024-04-12', 600.00, 'High floor', 'CUST003')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK004', '2024-04-15', '2024-04-17', 800.00, 'Extra pillows', 'CUST004')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK005', '2024-04-20', '2024-04-22', 500.00, 'No smoking room', 'CUST005')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK006', '2024-04-25', '2024-04-27', 900.00, 'Ocean view', 'CUST006')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK007', '2024-05-01', '2024-05-03', 750.00, 'Breakfast included', 'CUST007')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK008', '2024-05-05', '2024-05-07', 650.00, 'Late check-out', 'CUST008')
INSERT INTO bookings (booking_id, start_date, end_date, deposit, note, customer_id) VALUES
('BOOK009', '2024-05-15', '2024-05-17', 650.00, 'Late check-out', 'CUST009')


-- Booking-Room relationships
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK001', 'ROOM101')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK002', 'ROOM102')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK003', 'ROOM103')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK004', 'ROOM104')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK005', 'ROOM105')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK006', 'ROOM106')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK007', 'ROOM107')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK008', 'ROOM108')
INSERT INTO bookings_rooms (booking_id, room_id) VALUES
('BOOK009', 'ROOM109')
-- Bills
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK001', 2, 200.00, 2200.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK002', 2, 250.00, 3250.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK003', 2, 300.00, 4600.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK004', 2, 350.00, 3950.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK005', 2, 180.00, 1980.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK006', 2, 400.00, 3800.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK007', 2, 500.00, 5400.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK008', 2, 450.00, 6450.00)
INSERT INTO bills (bill_id, days, service_fee, total_amount) VALUES
('BOOK009', 3, 550.00, 6450.00)



select c.* from bookings b
join customers c
on b.customer_id = c.customer_id
join bookings_rooms br
on br.booking_id = b.booking_id
join rooms r
on r.room_id = br.room_id
where year(b.start_date) = 2024 and month(b.start_date) = 4 and r.type ='Single'

select r.type from rooms r
where r.bed_option = 'King Bed'


SELECT * FROM [dbo].[bills];
SELECT * FROM [dbo].[bookings];
SELECT * FROM [dbo].[bookings_rooms];
SELECT * FROM [dbo].[customers];
SELECT * FROM [dbo].[rooms];
