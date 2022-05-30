--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS interprog;
--
-- Name: interprog; Type: DATABASE; Schema: -; Owner: root
--

CREATE DATABASE interprog WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


ALTER DATABASE interprog OWNER TO root;

\connect interprog

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.address (
    id integer NOT NULL,
    title character varying,
    context character varying NOT NULL,
    region character varying NOT NULL,
    district character varying NOT NULL,
    directions character varying
);


ALTER TABLE public.address OWNER TO root;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_id_seq OWNER TO root;

--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    name character varying,
    surname character varying,
    customertypeid integer NOT NULL,
    phone character varying NOT NULL,
    email character varying NOT NULL,
    companytitle character varying,
    taxnumber character varying(255),
    taxadministration character varying(255),
    addressid integer
);


ALTER TABLE public.customer OWNER TO root;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO root;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: customertype; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.customertype (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.customertype OWNER TO root;

--
-- Name: customertype_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.customertype_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customertype_id_seq OWNER TO root;

--
-- Name: customertype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.customertype_id_seq OWNED BY public.customertype.id;


--
-- Name: document; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.document (
    id integer NOT NULL,
    filepath character varying,
    filename character varying,
    filetype character varying
);


ALTER TABLE public.document OWNER TO postgres;

--
-- Name: document_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.document_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.document_id_seq OWNER TO postgres;

--
-- Name: document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.document_id_seq OWNED BY public.document.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.employee (
    id integer NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    phone character varying NOT NULL,
    color character varying,
    password character varying NOT NULL,
    roleid integer NOT NULL
);


ALTER TABLE public.employee OWNER TO root;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO root;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: employeexsquad; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.employeexsquad (
    employeeid integer NOT NULL,
    squadid integer NOT NULL
);


ALTER TABLE public.employeexsquad OWNER TO root;

--
-- Name: employeexwarehouse; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.employeexwarehouse (
    employeeid integer NOT NULL,
    warehouseid integer NOT NULL
);


ALTER TABLE public.employeexwarehouse OWNER TO root;

--
-- Name: job; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.job (
    id integer NOT NULL,
    creationdate timestamp without time zone DEFAULT now() NOT NULL,
    description character varying NOT NULL,
    date timestamp without time zone NOT NULL,
    statusid integer DEFAULT 1 NOT NULL,
    ownerid integer DEFAULT 1 NOT NULL,
    customerid integer NOT NULL
);


ALTER TABLE public.job OWNER TO root;

--
-- Name: job_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.job_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_id_seq OWNER TO root;

--
-- Name: job_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.job_id_seq OWNED BY public.job.id;


--
-- Name: jobxemployee; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.jobxemployee (
    jobid integer NOT NULL,
    employeeid integer NOT NULL
);


ALTER TABLE public.jobxemployee OWNER TO root;

--
-- Name: jobxproduct; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.jobxproduct (
    jobid integer NOT NULL,
    productid integer NOT NULL
);


ALTER TABLE public.jobxproduct OWNER TO root;

--
-- Name: product; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying NOT NULL,
    serialnumber character varying,
    unit character varying NOT NULL,
    purchaseprice integer NOT NULL,
    saleprice integer NOT NULL,
    vat integer NOT NULL,
    quantity integer,
    warehouseid integer,
    documentid integer
);


ALTER TABLE public.product OWNER TO root;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO root;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO root;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO root;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: squad; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.squad (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.squad OWNER TO root;

--
-- Name: squad_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.squad_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.squad_id_seq OWNER TO root;

--
-- Name: squad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.squad_id_seq OWNED BY public.squad.id;


--
-- Name: status; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.status (
    id integer NOT NULL,
    name character varying NOT NULL,
    color character varying
);


ALTER TABLE public.status OWNER TO root;

--
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO root;

--
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- Name: warehouse; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.warehouse (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.warehouse OWNER TO root;

--
-- Name: warehouse_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.warehouse_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.warehouse_id_seq OWNER TO root;

--
-- Name: warehouse_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.warehouse_id_seq OWNED BY public.warehouse.id;


--
-- Name: address id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public.address_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: customertype id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customertype ALTER COLUMN id SET DEFAULT nextval('public.customertype_id_seq'::regclass);


--
-- Name: document id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document ALTER COLUMN id SET DEFAULT nextval('public.document_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Name: job id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.job ALTER COLUMN id SET DEFAULT nextval('public.job_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: squad id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.squad ALTER COLUMN id SET DEFAULT nextval('public.squad_id_seq'::regclass);


--
-- Name: status id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- Name: warehouse id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.warehouse ALTER COLUMN id SET DEFAULT nextval('public.warehouse_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.address (id, title, context, region, district, directions) VALUES (1, 'West Terrace', 'West Terrace', 'West Terrace', 'West Terrace', 'West Terrace');
INSERT INTO public.address (id, title, context, region, district, directions) VALUES (2, '6 New Castle Hill', '6 New Castle Hill', '6 New Castle Hill', '6 New Castle Hill', '6 New Castle Hill');
INSERT INTO public.address (id, title, context, region, district, directions) VALUES (3, '512 Pierstorff Junction', '512 Pierstorff Junction', '512 Pierstorff Junction', '512 Pierstorff Junction', '512 Pierstorff Junction');
INSERT INTO public.address (id, title, context, region, district, directions) VALUES (4, '2338 5th Court', '2338 5th Court', '2338 5th Court', '2338 5th Court', '2338 5th Court');


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.customer (id, name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (1, 'Kizzee', 'Durnell', 1, '9573595081', 'kdurnell0@blogtalkradio.com', 'Wordpedia', '880-24-4750', 'Kizzee Durnell', 1);
INSERT INTO public.customer (id, name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (2, 'Adolpho', 'Plaistowe', 1, '6536882107', 'aplaistowe1@cbc.ca', 'Fadeo', '	642-30-9432', 'Adolpho Plaistowe', 2);
INSERT INTO public.customer (id, name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (3, 'Craggy', 'Bentame', 1, '9776827141', 'cbentame2@bing.com', 'Twiyo', '780-75-6690', 'Craggy Bentame', 3);
INSERT INTO public.customer (id, name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (4, 'Cinderella', 'Bristoe', 1, '8025499171', 'cbristoe9@dedecms.com', 'Brightdog', '844-03-2963', 'Cinderella Bristoe', 4);


--
-- Data for Name: customertype; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.customertype (id, name) VALUES (1, 'corporate');
INSERT INTO public.customertype (id, name) VALUES (2, 'individual');


--
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.document (id, filepath, filename, filetype) VALUES (15, '/home/csgn/Desktop/interprog/uploads/iphone-12-family-select-2021.jpeg', 'iphone-12-family-select-2021.jpeg', 'image/jpeg');
INSERT INTO public.document (id, filepath, filename, filetype) VALUES (16, '/home/csgn/Desktop/interprog/uploads/MsiGF63-8RD__1__01.jpeg', 'MsiGF63-8RD__1__01.jpeg', 'image/jpeg');


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (1, 'admin', 'admin', '0', '#000000', 'admin', 1);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (2, 'Karee', 'Clarricoates', '3941690424', '#3bc49b', 'zkBgUyt', 2);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (3, 'Glori', 'Mauvin', '6997749639', '#b1a635', 'WdknARm3X', 2);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (4, 'Ailis', 'Alesio', '7442768198', '#ffa70f', '0Pu2d2H', 2);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (5, 'Corrianne', 'Fratson', '9147156693', '#fea4a4', 'J1QSc6SXbsny', 2);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (6, 'Aggi', 'Ratnage', '6714853340', '#e13ddb', 'OE2CBF6', 2);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (7, 'Joice', 'Hovie', '8749128382', '#c70000', '0qeVvyz2Rik', 2);


--
-- Data for Name: employeexsquad; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (2, 1);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (2, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (2, 3);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (3, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (3, 3);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (3, 4);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (3, 5);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (4, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (4, 3);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (4, 4);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (4, 5);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (5, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (5, 3);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (5, 4);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (6, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (6, 3);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (7, 1);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (7, 2);
INSERT INTO public.employeexsquad (employeeid, squadid) VALUES (7, 3);


--
-- Data for Name: employeexwarehouse; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (2, 1);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (2, 2);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (2, 3);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (3, 1);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (3, 2);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (4, 8);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (5, 4);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (6, 5);
INSERT INTO public.employeexwarehouse (employeeid, warehouseid) VALUES (7, 10);


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.job (id, creationdate, description, date, statusid, ownerid, customerid) VALUES (4, '2022-05-30 22:35:49.966615', 'DESCRIPTION-3', '2022-06-10 00:00:00', 1, 1, 3);
INSERT INTO public.job (id, creationdate, description, date, statusid, ownerid, customerid) VALUES (2, '2022-05-30 00:00:00', 'DESCRIPTION-2', '2022-05-31 00:00:00', 4, 1, 1);
INSERT INTO public.job (id, creationdate, description, date, statusid, ownerid, customerid) VALUES (1, '2022-05-30 00:00:00', 'DESCRIPTION-1', '2022-06-02 00:00:00', 2, 1, 1);
INSERT INTO public.job (id, creationdate, description, date, statusid, ownerid, customerid) VALUES (3, '2022-05-30 00:00:00', 'DESCRIPTION-1', '2022-06-11 00:00:00', 3, 1, 2);


--
-- Data for Name: jobxemployee; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (4, 2);
INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (4, 3);
INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (2, 2);
INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (1, 2);
INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (3, 2);
INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (3, 3);


--
-- Data for Name: jobxproduct; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.jobxproduct (jobid, productid) VALUES (4, 13);
INSERT INTO public.jobxproduct (jobid, productid) VALUES (4, 14);
INSERT INTO public.jobxproduct (jobid, productid) VALUES (2, 14);
INSERT INTO public.jobxproduct (jobid, productid) VALUES (1, 13);
INSERT INTO public.jobxproduct (jobid, productid) VALUES (3, 13);
INSERT INTO public.jobxproduct (jobid, productid) VALUES (3, 14);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.product (id, name, serialnumber, unit, purchaseprice, saleprice, vat, quantity, warehouseid, documentid) VALUES (13, 'IPhone', '123213', 'number', 8000, 9000, 18, 10, 1, 15);
INSERT INTO public.product (id, name, serialnumber, unit, purchaseprice, saleprice, vat, quantity, warehouseid, documentid) VALUES (14, 'MSI', '124124', 'number', 14000, 17000, 0, 0, 1, 16);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.role (id, name) VALUES (1, 'owner');
INSERT INTO public.role (id, name) VALUES (2, 'user');


--
-- Data for Name: squad; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.squad (id, name) VALUES (1, 'SQUAD-1');
INSERT INTO public.squad (id, name) VALUES (2, 'SQUAD-2');
INSERT INTO public.squad (id, name) VALUES (3, 'SQUAD-3');
INSERT INTO public.squad (id, name) VALUES (4, 'SQUAD-4');
INSERT INTO public.squad (id, name) VALUES (5, 'SQUAD-5');


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.status (id, name, color) VALUES (1, 'open', '#4f4f4f');
INSERT INTO public.status (id, name, color) VALUES (2, 'close', '#ff5959');
INSERT INTO public.status (id, name, color) VALUES (3, 'done', '#82ff69');
INSERT INTO public.status (id, name, color) VALUES (4, 'preparing', '#5f69ec');


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.warehouse (id, name) VALUES (1, 'WAREHOUSE-1');
INSERT INTO public.warehouse (id, name) VALUES (2, 'WAREHOUSE-2');
INSERT INTO public.warehouse (id, name) VALUES (3, 'WAREHOUSE-3');
INSERT INTO public.warehouse (id, name) VALUES (4, 'WAREHOUSE-4');
INSERT INTO public.warehouse (id, name) VALUES (5, 'WAREHOUSE-5');
INSERT INTO public.warehouse (id, name) VALUES (6, 'WAREHOUSE-6');
INSERT INTO public.warehouse (id, name) VALUES (7, 'WAREHOUSE-7');
INSERT INTO public.warehouse (id, name) VALUES (8, 'WAREHOUSE-8');
INSERT INTO public.warehouse (id, name) VALUES (9, 'WAREHOUSE-9');
INSERT INTO public.warehouse (id, name) VALUES (10, 'WAREHOUSE-10');


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.address_id_seq', 1, false);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.customer_id_seq', 1, false);


--
-- Name: customertype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.customertype_id_seq', 2, true);


--
-- Name: document_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.document_id_seq', 16, true);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.employee_id_seq', 1, true);


--
-- Name: job_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.job_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.product_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.role_id_seq', 2, true);


--
-- Name: squad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.squad_id_seq', 1, false);


--
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.status_id_seq', 3, true);


--
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.warehouse_id_seq', 1, false);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- Name: customer customer_phone_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_phone_key UNIQUE (phone);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: customertype customertype_name_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customertype
    ADD CONSTRAINT customertype_name_key UNIQUE (name);


--
-- Name: customertype customertype_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customertype
    ADD CONSTRAINT customertype_pkey PRIMARY KEY (id);


--
-- Name: document document_filepath_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_filepath_key UNIQUE (filepath);


--
-- Name: document document_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (id);


--
-- Name: employee employee_phone_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_phone_key UNIQUE (phone);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: employeexsquad employeexsquad_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_pkey PRIMARY KEY (employeeid, squadid);


--
-- Name: employeexwarehouse employeexwarehouse_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_pkey PRIMARY KEY (employeeid, warehouseid);


--
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (id);


--
-- Name: jobxemployee jobxemployee_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_pkey PRIMARY KEY (jobid, employeeid);


--
-- Name: jobxproduct jobxproduct_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_pkey PRIMARY KEY (jobid, productid);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product product_serialnumber_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_serialnumber_key UNIQUE (serialnumber);


--
-- Name: role role_name_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE (name);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: squad squad_name_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.squad
    ADD CONSTRAINT squad_name_key UNIQUE (name);


--
-- Name: squad squad_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.squad
    ADD CONSTRAINT squad_pkey PRIMARY KEY (id);


--
-- Name: status status_name_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_name_key UNIQUE (name);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: warehouse warehouse_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.warehouse
    ADD CONSTRAINT warehouse_pkey PRIMARY KEY (id);


--
-- Name: employee employee_roleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_roleid_fkey FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: employeexsquad employeexsquad_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexsquad employeexsquad_squadid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_squadid_fkey FOREIGN KEY (squadid) REFERENCES public.squad(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexwarehouse employeexwarehouse_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexwarehouse employeexwarehouse_warehouseid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_warehouseid_fkey FOREIGN KEY (warehouseid) REFERENCES public.warehouse(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: customer fk_address; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_address FOREIGN KEY (addressid) REFERENCES public.address(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: customer fk_customertype; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customertype FOREIGN KEY (customertypeid) REFERENCES public.customertype(id) ON UPDATE CASCADE;


--
-- Name: job job_customerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_customerid_fkey FOREIGN KEY (customerid) REFERENCES public.customer(id) ON UPDATE CASCADE;


--
-- Name: job job_ownerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_ownerid_fkey FOREIGN KEY (ownerid) REFERENCES public.employee(id) ON UPDATE CASCADE;


--
-- Name: job job_statusid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_statusid_fkey FOREIGN KEY (statusid) REFERENCES public.status(id) ON UPDATE CASCADE;


--
-- Name: jobxemployee jobxemployee_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxemployee jobxemployee_jobid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_jobid_fkey FOREIGN KEY (jobid) REFERENCES public.job(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxproduct jobxproduct_jobid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_jobid_fkey FOREIGN KEY (jobid) REFERENCES public.job(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxproduct jobxproduct_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_productid_fkey FOREIGN KEY (productid) REFERENCES public.product(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product product_documentid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_documentid_fkey FOREIGN KEY (documentid) REFERENCES public.document(id);


--
-- Name: product product_warehouseid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_warehouseid_fkey FOREIGN KEY (warehouseid) REFERENCES public.warehouse(id);


--
-- PostgreSQL database dump complete
--

