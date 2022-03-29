--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

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
    warehouseid integer
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



--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: customertype; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.customertype (id, name) VALUES (1, 'corporate');
INSERT INTO public.customertype (id, name) VALUES (2, 'individual');


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (1, 'admin', 'admin', '0', '#000000', 'admin', 1);


--
-- Data for Name: employeexsquad; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: employeexwarehouse; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: jobxemployee; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: jobxproduct; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.role (id, name) VALUES (1, 'owner');
INSERT INTO public.role (id, name) VALUES (2, 'user');


--
-- Data for Name: squad; Type: TABLE DATA; Schema: public; Owner: root
--



--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: root
--

INSERT INTO public.status (id, name, color) VALUES (1, 'open', '#4f4f4f');
INSERT INTO public.status (id, name, color) VALUES (2, 'close', '#ff5959');
INSERT INTO public.status (id, name, color) VALUES (3, 'done', '#82ff69');


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: public; Owner: root
--



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
-- Name: product product_warehouseid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_warehouseid_fkey FOREIGN KEY (warehouseid) REFERENCES public.warehouse(id);


--
-- PostgreSQL database dump complete
--

