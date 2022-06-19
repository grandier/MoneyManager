--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6
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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Kategori_Pendapatan; Type: TABLE; Schema: public; Owner: kemas_sbd
--

CREATE TABLE public."Kategori_Pendapatan" (
    "KatPend_Id" integer NOT NULL,
    "Jenis" text NOT NULL,
    "User_Id" integer NOT NULL
);


ALTER TABLE public."Kategori_Pendapatan" OWNER TO kemas_sbd;

--
-- Name: Kategori_Pendapatan_KatPend_Id_seq; Type: SEQUENCE; Schema: public; Owner: kemas_sbd
--

CREATE SEQUENCE public."Kategori_Pendapatan_KatPend_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Kategori_Pendapatan_KatPend_Id_seq" OWNER TO kemas_sbd;

--
-- Name: Kategori_Pendapatan_KatPend_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kemas_sbd
--

ALTER SEQUENCE public."Kategori_Pendapatan_KatPend_Id_seq" OWNED BY public."Kategori_Pendapatan"."KatPend_Id";


--
-- Name: Kategori_Pengeluaran; Type: TABLE; Schema: public; Owner: kemas_sbd
--

CREATE TABLE public."Kategori_Pengeluaran" (
    "KatPeng_Id" integer NOT NULL,
    "Jenis" text NOT NULL,
    "User_Id" integer NOT NULL
);


ALTER TABLE public."Kategori_Pengeluaran" OWNER TO kemas_sbd;

--
-- Name: Kategori_Pengeluaran_KatPeng_ID_seq; Type: SEQUENCE; Schema: public; Owner: kemas_sbd
--

CREATE SEQUENCE public."Kategori_Pengeluaran_KatPeng_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Kategori_Pengeluaran_KatPeng_ID_seq" OWNER TO kemas_sbd;

--
-- Name: Kategori_Pengeluaran_KatPeng_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kemas_sbd
--

ALTER SEQUENCE public."Kategori_Pengeluaran_KatPeng_ID_seq" OWNED BY public."Kategori_Pengeluaran"."KatPeng_Id";


--
-- Name: Pendapatan; Type: TABLE; Schema: public; Owner: kemas_sbd
--

CREATE TABLE public."Pendapatan" (
    "Pendapatan_Id" integer NOT NULL,
    "Deskripsi" text NOT NULL,
    "Jumlah" bigint NOT NULL,
    "Tanggal" date NOT NULL,
    "User_Id" integer NOT NULL,
    "KatPend_Id" integer NOT NULL
);


ALTER TABLE public."Pendapatan" OWNER TO kemas_sbd;

--
-- Name: Pendapatan_Pendapatan_Id_seq; Type: SEQUENCE; Schema: public; Owner: kemas_sbd
--

CREATE SEQUENCE public."Pendapatan_Pendapatan_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Pendapatan_Pendapatan_Id_seq" OWNER TO kemas_sbd;

--
-- Name: Pendapatan_Pendapatan_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kemas_sbd
--

ALTER SEQUENCE public."Pendapatan_Pendapatan_Id_seq" OWNED BY public."Pendapatan"."Pendapatan_Id";


--
-- Name: Pengeluaran; Type: TABLE; Schema: public; Owner: kemas_sbd
--

CREATE TABLE public."Pengeluaran" (
    "Pengeluaran_Id" integer NOT NULL,
    "Deskripsi" text NOT NULL,
    "Jumlah" bigint NOT NULL,
    "Tanggal" date NOT NULL,
    "User_Id" integer NOT NULL,
    "KatPeng_Id" integer NOT NULL
);


ALTER TABLE public."Pengeluaran" OWNER TO kemas_sbd;

--
-- Name: pengeluaran_Pengeluaran_Id_seq; Type: SEQUENCE; Schema: public; Owner: kemas_sbd
--

CREATE SEQUENCE public."pengeluaran_Pengeluaran_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."pengeluaran_Pengeluaran_Id_seq" OWNER TO kemas_sbd;

--
-- Name: pengeluaran_Pengeluaran_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kemas_sbd
--

ALTER SEQUENCE public."pengeluaran_Pengeluaran_Id_seq" OWNED BY public."Pengeluaran"."Pengeluaran_Id";


--
-- Name: users; Type: TABLE; Schema: public; Owner: kemas_sbd
--

CREATE TABLE public.users (
    "User_Id" integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL
);


ALTER TABLE public.users OWNER TO kemas_sbd;

--
-- Name: user_User_Id_seq; Type: SEQUENCE; Schema: public; Owner: kemas_sbd
--

CREATE SEQUENCE public."user_User_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."user_User_Id_seq" OWNER TO kemas_sbd;

--
-- Name: user_User_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kemas_sbd
--

ALTER SEQUENCE public."user_User_Id_seq" OWNED BY public.users."User_Id";


--
-- Name: Kategori_Pendapatan KatPend_Id; Type: DEFAULT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pendapatan" ALTER COLUMN "KatPend_Id" SET DEFAULT nextval('public."Kategori_Pendapatan_KatPend_Id_seq"'::regclass);


--
-- Name: Kategori_Pengeluaran KatPeng_Id; Type: DEFAULT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pengeluaran" ALTER COLUMN "KatPeng_Id" SET DEFAULT nextval('public."Kategori_Pengeluaran_KatPeng_ID_seq"'::regclass);


--
-- Name: Pendapatan Pendapatan_Id; Type: DEFAULT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pendapatan" ALTER COLUMN "Pendapatan_Id" SET DEFAULT nextval('public."Pendapatan_Pendapatan_Id_seq"'::regclass);


--
-- Name: Pengeluaran Pengeluaran_Id; Type: DEFAULT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pengeluaran" ALTER COLUMN "Pengeluaran_Id" SET DEFAULT nextval('public."pengeluaran_Pengeluaran_Id_seq"'::regclass);


--
-- Name: users User_Id; Type: DEFAULT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public.users ALTER COLUMN "User_Id" SET DEFAULT nextval('public."user_User_Id_seq"'::regclass);


--
-- Data for Name: Kategori_Pendapatan; Type: TABLE DATA; Schema: public; Owner: kemas_sbd
--

COPY public."Kategori_Pendapatan" ("KatPend_Id", "Jenis", "User_Id") FROM stdin;
2	Makan	1
3	Gaji	2
4	Hadiah	1
5	Hadiah 2	1
6	THR	2
7	Hadiah Mantap	2
8	Nyoba 1	1
9	Nyoba 2	1
10	Nyoba 3	1
11	Nyoba 4	1
12	Nyoba 5	1
1	Gaji/THR	1
13	Hebat	1
14	Nyoba Hehe	1
15	Sunatan Hebat	2
17	BERHASIL	1
\.


--
-- Data for Name: Kategori_Pengeluaran; Type: TABLE DATA; Schema: public; Owner: kemas_sbd
--

COPY public."Kategori_Pengeluaran" ("KatPeng_Id", "Jenis", "User_Id") FROM stdin;
1	Makan/Minum	1
2	Makan	2
3	Hadiah	1
5	Aku Hebat	1
4	Kamu Hebat	1
6	Hebat	1
7	Jalan Jalan Dengan Mobil	2
8	Mantap	1
9	Nyoba	1
\.


--
-- Data for Name: Pendapatan; Type: TABLE DATA; Schema: public; Owner: kemas_sbd
--

COPY public."Pendapatan" ("Pendapatan_Id", "Deskripsi", "Jumlah", "Tanggal", "User_Id", "KatPend_Id") FROM stdin;
3	THR2	1000000	2022-12-02	1	1
5	Gaji Pembukaan	1000000	2022-12-02	1	1
6	Gaji Pembukaan	1000000	2022-12-02	2	1
7	Gaji awal	200000	2022-11-01	2	1
9	Gaji Pembukaan	1000000	2022-12-02	2	3
11	Warteg	14000	2022-04-25	1	2
12	Ulang Tahun	200000	2022-03-04	1	4
13	Gaji Besar	1000000	2022-08-26	1	1
14	Mie Ayam	10000	2022-06-18	1	2
\.


--
-- Data for Name: Pengeluaran; Type: TABLE DATA; Schema: public; Owner: kemas_sbd
--

COPY public."Pengeluaran" ("Pengeluaran_Id", "Deskripsi", "Jumlah", "Tanggal", "User_Id", "KatPeng_Id") FROM stdin;
2	Bukber	50000	2022-11-02	1	1
3	Bukber	50000	2022-11-02	2	1
5	Clubbing	10000000	2022-11-17	1	8
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: kemas_sbd
--

COPY public.users ("User_Id", username, password) FROM stdin;
1	kemas	$2a$10$NLIYiJPz/sowKyW/VJktduCD3xBduZSZScx8kaALYPfOCUFMXBiVm
2	emir	$2a$10$uRNFXOmmkgb7n.9NA4.MpeYDYXv/PJc/u6t5PIf6FGczpYP9pm.A6
\.


--
-- Name: Kategori_Pendapatan_KatPend_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: kemas_sbd
--

SELECT pg_catalog.setval('public."Kategori_Pendapatan_KatPend_Id_seq"', 17, true);


--
-- Name: Kategori_Pengeluaran_KatPeng_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: kemas_sbd
--

SELECT pg_catalog.setval('public."Kategori_Pengeluaran_KatPeng_ID_seq"', 10, true);


--
-- Name: Pendapatan_Pendapatan_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: kemas_sbd
--

SELECT pg_catalog.setval('public."Pendapatan_Pendapatan_Id_seq"', 20, true);


--
-- Name: pengeluaran_Pengeluaran_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: kemas_sbd
--

SELECT pg_catalog.setval('public."pengeluaran_Pengeluaran_Id_seq"', 9, true);


--
-- Name: user_User_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: kemas_sbd
--

SELECT pg_catalog.setval('public."user_User_Id_seq"', 8, true);


--
-- Name: Kategori_Pendapatan Kategori_Pendapatan_pkey; Type: CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pendapatan"
    ADD CONSTRAINT "Kategori_Pendapatan_pkey" PRIMARY KEY ("KatPend_Id");


--
-- Name: Kategori_Pengeluaran Kategori_Pengeluaran_pkey; Type: CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pengeluaran"
    ADD CONSTRAINT "Kategori_Pengeluaran_pkey" PRIMARY KEY ("KatPeng_Id");


--
-- Name: Pendapatan Pendapatan_pkey; Type: CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pendapatan"
    ADD CONSTRAINT "Pendapatan_pkey" PRIMARY KEY ("Pendapatan_Id");


--
-- Name: Pengeluaran pengeluaran_pkey; Type: CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pengeluaran"
    ADD CONSTRAINT pengeluaran_pkey PRIMARY KEY ("Pengeluaran_Id");


--
-- Name: users user_pkey; Type: CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pkey PRIMARY KEY ("User_Id");


--
-- Name: Kategori_Pendapatan Kategori_Pendapatan_User_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pendapatan"
    ADD CONSTRAINT "Kategori_Pendapatan_User_Id_fkey" FOREIGN KEY ("User_Id") REFERENCES public.users("User_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: Kategori_Pengeluaran Kategori_Pengeluaran_User_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Kategori_Pengeluaran"
    ADD CONSTRAINT "Kategori_Pengeluaran_User_Id_fkey" FOREIGN KEY ("User_Id") REFERENCES public.users("User_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: Pendapatan Pendapatan_KatPend_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pendapatan"
    ADD CONSTRAINT "Pendapatan_KatPend_Id_fkey" FOREIGN KEY ("KatPend_Id") REFERENCES public."Kategori_Pendapatan"("KatPend_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: Pendapatan Pendapatan_User_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pendapatan"
    ADD CONSTRAINT "Pendapatan_User_Id_fkey" FOREIGN KEY ("User_Id") REFERENCES public.users("User_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: Pengeluaran Pengeluaran_KatPeng_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pengeluaran"
    ADD CONSTRAINT "Pengeluaran_KatPeng_Id_fkey" FOREIGN KEY ("KatPeng_Id") REFERENCES public."Kategori_Pengeluaran"("KatPeng_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: Pengeluaran Pengeluaran_User_Id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: kemas_sbd
--

ALTER TABLE ONLY public."Pengeluaran"
    ADD CONSTRAINT "Pengeluaran_User_Id_fkey" FOREIGN KEY ("User_Id") REFERENCES public.users("User_Id") ON DELETE CASCADE NOT VALID;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: azure_pg_admin
--

REVOKE ALL ON SCHEMA public FROM azuresu;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO azure_pg_admin;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: FUNCTION pg_replication_origin_advance(text, pg_lsn); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_advance(text, pg_lsn) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_create(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_create(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_drop(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_drop(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_oid(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_oid(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_progress(text, boolean); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_progress(text, boolean) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_is_setup(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_is_setup() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_progress(boolean); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_progress(boolean) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_session_setup(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_session_setup(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_xact_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_xact_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_replication_origin_xact_setup(pg_lsn, timestamp with time zone); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_replication_origin_xact_setup(pg_lsn, timestamp with time zone) TO azure_pg_admin;


--
-- Name: FUNCTION pg_show_replication_origin_status(OUT local_id oid, OUT external_id text, OUT remote_lsn pg_lsn, OUT local_lsn pg_lsn); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_show_replication_origin_status(OUT local_id oid, OUT external_id text, OUT remote_lsn pg_lsn, OUT local_lsn pg_lsn) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset(); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset() TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_shared(text); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_shared(text) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_single_function_counters(oid); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_single_function_counters(oid) TO azure_pg_admin;


--
-- Name: FUNCTION pg_stat_reset_single_table_counters(oid); Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT ALL ON FUNCTION pg_catalog.pg_stat_reset_single_table_counters(oid) TO azure_pg_admin;


--
-- Name: COLUMN pg_config.name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(name) ON TABLE pg_catalog.pg_config TO azure_pg_admin;


--
-- Name: COLUMN pg_config.setting; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(setting) ON TABLE pg_catalog.pg_config TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.line_number; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(line_number) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.type; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(type) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.database; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(database) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.user_name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(user_name) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.address; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(address) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.netmask; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(netmask) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.auth_method; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(auth_method) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.options; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(options) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_hba_file_rules.error; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(error) ON TABLE pg_catalog.pg_hba_file_rules TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.local_id; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(local_id) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.external_id; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(external_id) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.remote_lsn; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(remote_lsn) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_replication_origin_status.local_lsn; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(local_lsn) ON TABLE pg_catalog.pg_replication_origin_status TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.name; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(name) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.off; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(off) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.size; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(size) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_shmem_allocations.allocated_size; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(allocated_size) ON TABLE pg_catalog.pg_shmem_allocations TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.starelid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(starelid) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staattnum; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staattnum) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stainherit; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stainherit) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanullfrac; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanullfrac) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stawidth; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stawidth) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stadistinct; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stadistinct) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stakind5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stakind5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.staop5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(staop5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stacoll5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stacoll5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stanumbers5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stanumbers5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues1; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues1) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues2; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues2) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues3; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues3) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues4; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues4) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_statistic.stavalues5; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(stavalues5) ON TABLE pg_catalog.pg_statistic TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.oid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(oid) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subdbid; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subdbid) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subname; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subname) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subowner; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subowner) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subenabled; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subenabled) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subconninfo; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subconninfo) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subslotname; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subslotname) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subsynccommit; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subsynccommit) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- Name: COLUMN pg_subscription.subpublications; Type: ACL; Schema: pg_catalog; Owner: azuresu
--

GRANT SELECT(subpublications) ON TABLE pg_catalog.pg_subscription TO azure_pg_admin;


--
-- PostgreSQL database dump complete
--

