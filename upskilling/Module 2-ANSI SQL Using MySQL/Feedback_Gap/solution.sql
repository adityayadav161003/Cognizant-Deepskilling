SELECT e.title FROM Events e LEFT JOIN Feedback f ON e.event_id = f.event_id WHERE f.feedback_id IS NULL;
