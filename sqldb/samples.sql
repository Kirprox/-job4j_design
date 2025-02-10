CREATE VIEW show_students_with_2_or_more_books AS
SELECT s.name, COUNT(b.id) AS book_count, a.name AS author_name
FROM students AS s
JOIN orders AS o ON s.id = o.student_id
JOIN books AS b ON o.book_id = b.id
JOIN authors AS a ON b.author_id = a.id
GROUP BY s.name, a.name
HAVING COUNT(b.id) >= 2;

select * from show_students_with_2_or_more_books