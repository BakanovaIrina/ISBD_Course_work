-- Индекс на столбец status_location в таблице gift_status
CREATE INDEX idx_gift_status_status_location ON gift_status USING btree (status_location);

-- Индекс на столбец workstatus в таблице production
CREATE INDEX idx_production_workstatus ON production USING btree (workstatus);