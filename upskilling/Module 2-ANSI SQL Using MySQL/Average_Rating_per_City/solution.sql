SELECT city, AVG(rating) FROM Events e JOIN Feedback f ON e.event_id = f.event_id GROUP BY city;
