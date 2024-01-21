Application for scraping jobs from jobs.techstars.com by specific job function

The URLs:
- http://localhost:8080/techstars-parser?function=DESIGN - parsing by function;

- http://localhost:8080/techstars/all - getting all jobs from the Db;

- http://localhost:8080/techstars/by-function?function=DESIGN - getting the jobs by function;

- http://localhost:8080/techstars/download-csv?function=DESIGN - downloading csv file with jobs by function.

function can be:
- ACCOUNTING_AND_FINANCE,
- ADMINISTRATION,
- DATA_SCIENCE,
- CUSTOMER_SERVICE,
- DESIGN,
- IT("IT"),
- LEGAL("Legal"),
- MARKETING_AND_COMMUNICATIONS,
- OPERATIONS,
- OTHER_ENGINEERING,
- PEOPLE_AND_HR,
- PRODUCT,
- QUALITY_ASSURANCE,
- SALES_AND_BUSINESS_DEVELOPMENT,
- SOFTWARE_ENGINEERING;

The video that shows how the app works: https://drive.google.com/file/d/1QN_38eQGQNGGyS4jYCrT3J2aCfmmHWdb/view?usp=sharing

(Spring Boot, Spring Data JPA, Spring Web, Maven, H2, Lombock, Jsoup, Super CSV)
