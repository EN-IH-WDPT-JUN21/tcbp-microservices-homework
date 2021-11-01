# validation-service

server.port=8006

_________________________________

### GET: /api/validation/email

takes:

    {
        "email": String
    }

returns `boolean` 

___________________________


### GET: /api/validation/phone

takes:

    {
        "phoneNumber": String
    }

returns `boolean`

______________________________

### GET: /api/validation/salesrep/{id}

returns `boolean` 
- exists: true;
- doesn't exist: false

----------

### GET: /api/validation/lead/{id}

returns `boolean`
- exists: true;
- doesn't exist: false

---------- 

### GET: /api/validation/opportunity/{id}

returns `boolean`
- exists: true;
- doesn't exist: false

----------

### GET: /api/validation/account/{id}

returns `boolean`
- exists: true;
- doesn't exist: false

----------

### GET: /api/validation/duplicate/lead

takes:

    {
        "contactName": String,
        "phoneNumber": String,
        "email": String,
        "companyName": String
    }

returns `boolean`
- is duplicate: true;
- not duplicate: false

---------------





