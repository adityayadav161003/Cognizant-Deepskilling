SELECT registration_date, COUNT(*) FROM Users GROUP BY registration_date ORDER BY registration_date DESC LIMIT 7;
