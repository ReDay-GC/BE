-- memory_date, record_date 컬럼을 DATETIME에서 DATE 타입으로 변경
-- ddl-auto: update는 기존 컬럼 타입을 변경하지 않으므로 직접 실행 필요
--
-- 실행 전 주의사항:
--   1. 서버 배포 중단 후 실행할 것
--   2. 실행 전 DB 백업 권장
--   3. 기존 DATETIME 값의 날짜 부분만 유지되고 시간 부분은 제거됨

ALTER TABLE memory MODIFY COLUMN memory_date DATE NOT NULL;
ALTER TABLE record MODIFY COLUMN record_date DATE NOT NULL;
