CREATE DATABASE IF NOT EXISTS PnLApp;

CREATE TABLE PnLApp.pn_l_records (
    `pnl_record_id` INT,
    `portfolio_id` INT,
    `calculation_date` DATETIME,
    `realized_pn_l` DOUBLE,
    `unrealized_pn_l` DOUBLE,
    `total_pn_l` DOUBLE,
PRIMARY KEY (pn_l_record_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE PnLApp.instrument_pn_ls (
    `instrument_pn_l_id` INT AUTO_INCREMENT,
    `pnl_record_id` INT,
    `instrument_id` INT,
    `realized_pn_l` DOUBLE,
    `unrealized_pn_l` DOUBLE,
PRIMARY KEY (instrument_pn_l_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE PnLApp.strategy_pn_ls (
    `strategy_pn_l_id` INT AUTO_INCREMENT,
    `pnl_record_id` INT,
    `strategy_id` INT,
    `realized_pn_l` DOUBLE,
    `unrealized_pn_l` DOUBLE,
PRIMARY KEY (strategy_pn_l_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE PnLApp.pn_l_adjustments (
    `pnl_adjustment_id` INT,
    `pnl_record_id` INT,
    `adjustment_type` VARCHAR(255),
    `amount` DOUBLE,
    `description` VARCHAR(255),
PRIMARY KEY (pn_l_adjustment_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
