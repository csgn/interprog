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

DROP DATABASE IF EXISTS postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

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

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: activitytype; Type: TYPE; Schema: public; Owner: root
--

CREATE TYPE public.activitytype AS ENUM (
    'job_created',
    'job_status_changed',
    'new_file_added',
    'update'
);


ALTER TYPE public.activitytype OWNER TO root;

--
-- Name: add_activity(); Type: FUNCTION; Schema: public; Owner: root
--

CREATE FUNCTION public.add_activity() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
    owner         varchar;
    activity_type activityType;
    activity_msg  varchar;
    activity_info varchar;
BEGIN
    owner := (SELECT name
              FROM Employee
              WHERE Employee.id = new.ownerId
    );

    IF old IS NULL THEN
        activity_type := 'job_created';
        activity_msg := (SELECT FORMAT('%s was created a new job', owner));
    ELSEIF old.statusId <> new.statusId THEN
        activity_type := 'job_status_changed';
        activity_msg := (SELECT FORMAT('%s was changed the job status', owner));
        activity_info := (SELECT FORMAT('New Status: %s', (
            SELECT name from Status
                WHERE id = new.statusId
            ) ));
    ELSEIF old.files <> new.files THEN
        activity_type := 'new_file_added';
        activity_msg := (SELECT FORMAT('%s added a new file', owner));
        activity_info := '%', new.files[ARRAY_UPPER(new.files, 1)];
    ELSE
        activity_type := 'update';
        activity_msg := (SELECT FORMAT('%s made an update', owner));
    END IF;

    INSERT INTO Activity (date, msg, info, type, ownerId, jobId)
    VALUES (NOW(), activity_msg, activity_info, activity_type, new.ownerId, new.id);

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.add_activity() OWNER TO root;

--
-- Name: agecalculator(date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.agecalculator(date_of_birth date) RETURNS integer
    LANGUAGE plpgsql
    AS $$
begin
      return (extract(year from NOW()) - extract(year from date_of_birth));
end;
$$;


ALTER FUNCTION public.agecalculator(date_of_birth date) OWNER TO postgres;

--
-- Name: indexof(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.indexof(letter character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare
  _index int;
begin
SELECT indx[1] from ARRAY_POSITIONS(ARRAY['a','b','c','d','e','f','g',
                             'h','i','j','k','l','m','n',
                             'o','p','q','r','s','t','u',
                             'v','w','x','y','z'], letter) as indx
  INTO _index;

  RETURN _index;
end;
$$;


ALTER FUNCTION public.indexof(letter character varying) OWNER TO postgres;

--
-- Name: solve(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.solve() RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare
    _result varchar;
  _letters varchar ARRAY DEFAULT ARRAY['a','b','c','d','e','f','g',
                                       'h','i','j','k','l','m','n',
                                       'o','p','q','r','s','t','u',
                                       'v','w','x','y','z'];
begin
    RETURN (select _letters[2]);
end;
$$;


ALTER FUNCTION public.solve() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id integer NOT NULL,
    title character varying,
    context character varying NOT NULL,
    region character varying NOT NULL,
    district character varying NOT NULL,
    directions character varying
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_id_seq OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_id_seq OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: customertype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customertype (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.customertype OWNER TO postgres;

--
-- Name: customertype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customertype_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customertype_id_seq OWNER TO postgres;

--
-- Name: customertype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customertype_id_seq OWNED BY public.customertype.id;


--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.employee OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO postgres;

--
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- Name: employeexsquad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employeexsquad (
    employeeid integer NOT NULL,
    squadid integer NOT NULL
);


ALTER TABLE public.employeexsquad OWNER TO postgres;

--
-- Name: employeexwarehouse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employeexwarehouse (
    employeeid integer NOT NULL,
    warehouseid integer NOT NULL
);


ALTER TABLE public.employeexwarehouse OWNER TO postgres;

--
-- Name: job; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.job OWNER TO postgres;

--
-- Name: job_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.job_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.job_id_seq OWNER TO postgres;

--
-- Name: job_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.job_id_seq OWNED BY public.job.id;


--
-- Name: jobxemployee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jobxemployee (
    jobid integer NOT NULL,
    employeeid integer NOT NULL
);


ALTER TABLE public.jobxemployee OWNER TO postgres;

--
-- Name: jobxproduct; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.jobxproduct (
    jobid integer NOT NULL,
    productid integer NOT NULL
);


ALTER TABLE public.jobxproduct OWNER TO postgres;

--
-- Name: letterarr; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.letterarr (
    arr character varying[] DEFAULT ARRAY['a'::text, 'b'::text, 'c'::text, 'd'::text, 'e'::text, 'f'::text, 'g'::text, 'h'::text, 'i'::text, 'j'::text, 'k'::text, 'l'::text, 'm'::text, 'n'::text, 'o'::text, 'p'::text, 'q'::text, 'r'::text, 's'::text, 't'::text, 'u'::text, 'v'::text, 'w'::text, 'x'::text, 'y'::text, 'z'::text]
);


ALTER TABLE public.letterarr OWNER TO postgres;

--
-- Name: letters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.letters (
    id integer,
    letter character varying
);


ALTER TABLE public.letters OWNER TO postgres;

--
-- Name: people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.people (
    name character varying
);


ALTER TABLE public.people OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
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


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: squad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.squad (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.squad OWNER TO postgres;

--
-- Name: squad_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.squad_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.squad_id_seq OWNER TO postgres;

--
-- Name: squad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.squad_id_seq OWNED BY public.squad.id;


--
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id integer NOT NULL,
    name character varying NOT NULL,
    color character varying
);


ALTER TABLE public.status OWNER TO postgres;

--
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_id_seq OWNER TO postgres;

--
-- Name: status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;


--
-- Name: warehouse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouse (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.warehouse OWNER TO postgres;

--
-- Name: warehouse_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.warehouse_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.warehouse_id_seq OWNER TO postgres;

--
-- Name: warehouse_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.warehouse_id_seq OWNED BY public.warehouse.id;


--
-- Name: address id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public.address_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: customertype id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customertype ALTER COLUMN id SET DEFAULT nextval('public.customertype_id_seq'::regclass);


--
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- Name: job id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job ALTER COLUMN id SET DEFAULT nextval('public.job_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: squad id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.squad ALTER COLUMN id SET DEFAULT nextval('public.squad_id_seq'::regclass);


--
-- Name: status id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);


--
-- Name: warehouse id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse ALTER COLUMN id SET DEFAULT nextval('public.warehouse_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.address (id, title, context, region, district, directions) VALUES (1, '214', '214', '214', '124', '124');


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customer (id, name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (1, 'customername1', 'customersurname1', 1, '124', 'asd@gmail.com', '214', '214', '124', 1);


--
-- Data for Name: customertype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customertype (id, name) VALUES (1, 'corporate');
INSERT INTO public.customertype (id, name) VALUES (2, 'individual');


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (1, 'admin', 'admin', '0', '#000000', 'admin', 1);
INSERT INTO public.employee (id, name, surname, phone, color, password, roleid) VALUES (2, 'employee1', 'employee1', '124', '#000000', '124124', 2);


--
-- Data for Name: employeexsquad; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: employeexwarehouse; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.job (id, creationdate, description, date, statusid, ownerid, customerid) VALUES (8, '2022-04-10 22:29:19.264401', 'asdasd123', '2022-04-12 00:00:00', 1, 1, 1);


--
-- Data for Name: jobxemployee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.jobxemployee (jobid, employeeid) VALUES (8, 2);


--
-- Data for Name: jobxproduct; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.jobxproduct (jobid, productid) VALUES (8, 2);


--
-- Data for Name: letterarr; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: letters; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.letters (id, letter) VALUES (1, 'a');
INSERT INTO public.letters (id, letter) VALUES (2, 'b');
INSERT INTO public.letters (id, letter) VALUES (3, 'c');


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.people (name) VALUES ('Greyson Tate Lebsack Jr.');
INSERT INTO public.people (name) VALUES ('Elmore Clementina O`Conner');
INSERT INTO public.people (name) VALUES ('Elmore Clementina O''Conner');


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, name, serialnumber, unit, purchaseprice, saleprice, vat, quantity, warehouseid) VALUES (1, 'PRODUCT-1', '125125', 'kg', 100, 124, 0, 10, 1);
INSERT INTO public.product (id, name, serialnumber, unit, purchaseprice, saleprice, vat, quantity, warehouseid) VALUES (2, 'PRODUCT-2', '12512', 'dozen', 142, 132, 8, 10, 2);


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id, name) VALUES (1, 'owner');
INSERT INTO public.role (id, name) VALUES (2, 'user');


--
-- Data for Name: squad; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.squad (id, name) VALUES (1, 'SQUAD-1');
INSERT INTO public.squad (id, name) VALUES (2, 'SQUAD-2');
INSERT INTO public.squad (id, name) VALUES (3, 'SQUAD-3');


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.status (id, name, color) VALUES (1, 'open', '#4f4f4f');
INSERT INTO public.status (id, name, color) VALUES (2, 'close', '#ff5959');
INSERT INTO public.status (id, name, color) VALUES (3, 'done', '#82ff69');


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouse (id, name) VALUES (1, 'WAREHOUSE-1');
INSERT INTO public.warehouse (id, name) VALUES (2, 'WAREHOUSE-2');
INSERT INTO public.warehouse (id, name) VALUES (3, 'WAREHOUSE-3');


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_id_seq', 1, true);


--
-- Name: customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_id_seq', 1, true);


--
-- Name: customertype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customertype_id_seq', 4, true);


--
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 4, true);


--
-- Name: job_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.job_id_seq', 10, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 2, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 4, true);


--
-- Name: squad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.squad_id_seq', 3, true);


--
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_id_seq', 5, true);


--
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouse_id_seq', 3, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- Name: customer customer_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_phone_key UNIQUE (phone);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: customertype customertype_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customertype
    ADD CONSTRAINT customertype_name_key UNIQUE (name);


--
-- Name: customertype customertype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customertype
    ADD CONSTRAINT customertype_pkey PRIMARY KEY (id);


--
-- Name: employee employee_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_phone_key UNIQUE (phone);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- Name: employeexsquad employeexsquad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_pkey PRIMARY KEY (employeeid, squadid);


--
-- Name: employeexwarehouse employeexwarehouse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_pkey PRIMARY KEY (employeeid, warehouseid);


--
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (id);


--
-- Name: jobxemployee jobxemployee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_pkey PRIMARY KEY (jobid, employeeid);


--
-- Name: jobxproduct jobxproduct_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_pkey PRIMARY KEY (jobid, productid);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: product product_serialnumber_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_serialnumber_key UNIQUE (serialnumber);


--
-- Name: role role_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_name_key UNIQUE (name);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: squad squad_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.squad
    ADD CONSTRAINT squad_name_key UNIQUE (name);


--
-- Name: squad squad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.squad
    ADD CONSTRAINT squad_pkey PRIMARY KEY (id);


--
-- Name: status status_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_name_key UNIQUE (name);


--
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- Name: warehouse warehouse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse
    ADD CONSTRAINT warehouse_pkey PRIMARY KEY (id);


--
-- Name: employee employee_roleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_roleid_fkey FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: employeexsquad employeexsquad_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexsquad employeexsquad_squadid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexsquad
    ADD CONSTRAINT employeexsquad_squadid_fkey FOREIGN KEY (squadid) REFERENCES public.squad(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexwarehouse employeexwarehouse_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: employeexwarehouse employeexwarehouse_warehouseid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeexwarehouse
    ADD CONSTRAINT employeexwarehouse_warehouseid_fkey FOREIGN KEY (warehouseid) REFERENCES public.warehouse(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: customer fk_address; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_address FOREIGN KEY (addressid) REFERENCES public.address(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: customer fk_customertype; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fk_customertype FOREIGN KEY (customertypeid) REFERENCES public.customertype(id) ON UPDATE CASCADE;


--
-- Name: job job_customerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_customerid_fkey FOREIGN KEY (customerid) REFERENCES public.customer(id) ON UPDATE CASCADE;


--
-- Name: job job_ownerid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_ownerid_fkey FOREIGN KEY (ownerid) REFERENCES public.employee(id) ON UPDATE CASCADE;


--
-- Name: job job_statusid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_statusid_fkey FOREIGN KEY (statusid) REFERENCES public.status(id) ON UPDATE CASCADE;


--
-- Name: jobxemployee jobxemployee_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxemployee jobxemployee_jobid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxemployee
    ADD CONSTRAINT jobxemployee_jobid_fkey FOREIGN KEY (jobid) REFERENCES public.job(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxproduct jobxproduct_jobid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_jobid_fkey FOREIGN KEY (jobid) REFERENCES public.job(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: jobxproduct jobxproduct_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobxproduct
    ADD CONSTRAINT jobxproduct_productid_fkey FOREIGN KEY (productid) REFERENCES public.product(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product product_warehouseid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_warehouseid_fkey FOREIGN KEY (warehouseid) REFERENCES public.warehouse(id);


--
-- PostgreSQL database dump complete
--

