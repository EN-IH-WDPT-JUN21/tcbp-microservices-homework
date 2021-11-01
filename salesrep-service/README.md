# salesrep-service
port: 8001

### GET: /salesrep

returns a list of:

    {
        "id": Long,
        "name": String
    }
    
### GET: /salesrep/{id}

returns

    {
        "id": Long,
        "name": String
    }
        
   

### POST: /salesrep

takes:

    {
      "name": String
    }
    
returns:

    {
        "id": Long,
        "name": String
    }
  
  
