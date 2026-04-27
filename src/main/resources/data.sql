INSERT INTO questions (id, text, active, created_at, updated_at)
VALUES
(1, '本当は今日は仕事を休みたいですか？', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '今の働き方に本音では満足していますか？', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '将来について、今ちょっと不安がありますか？', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON CONFLICT (id) DO NOTHING;