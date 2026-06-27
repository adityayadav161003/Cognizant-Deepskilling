SELECT strftime('%Y-%m', registration_date) as month, COUNT(*) FROM Registrations GROUP BY month ORDER BY month;
