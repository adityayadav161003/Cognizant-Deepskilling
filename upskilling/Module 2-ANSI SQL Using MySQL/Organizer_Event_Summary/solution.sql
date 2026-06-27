SELECT organizer_id, status, COUNT(*) FROM Events GROUP BY organizer_id, status;
