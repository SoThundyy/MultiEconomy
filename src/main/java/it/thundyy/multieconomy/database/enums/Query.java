package it.thundyy.multieconomy.database.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Query {

    CREATE_TABLE("""
            CREATE TABLE IF NOT EXISTS balances(
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            uuid CHAR(36) NOT NULL,
            currency_name VARCHAR(255) NOT NULL,
            balance DECIMAL(65, 2) NOT NULL DEFAULT '0'
            )
            """),
    INSERT("""
            INSERT INTO balances(uuid, currency_name, balance) VALUES(?, ?, ?)
            """),
    SELECT("""
            SELECT * FROM balances WHERE uuid = ? AND currency_name = ?
            """),
    SELECT_ALL("""
            SELECT * FROM balances WHERE uuid = ?
            """),
    UPDATE("""
            UPDATE balances SET balance = ? WHERE uuid = ? AND currency_name = ?
            """),
    ;

    private final String query;
}
