### Transaction Propagation example

### Setup
- Jdk 1.8
- Maven 3.x
- H2


-----------------------------------------------------------

**Saving `User` then `Message` Entity From `UserService` to `MessageService` & check console logs to see transaction propagation** 

-----------------------------------------------------------


#### 1. Without Transaction Annotation In Both Method
    
    - Both entity will be saved 
    
    - curl --location 'localhost:8080/user/without-transaction'

#### 2. Without Transaction Annotation In Both Method But Exception In `MessageService`
    
    - `User` entity will save but `Message` will throw exception
    
    - curl --location 'localhost:8080/user/without-transaction-exception'


#### 3. Transaction Annotation In Both Method (Propagation REQUIRED) By Default

    - Both entity will be saved 
    
    - curl --location 'localhost:8080/user/transaction-required'


#### 4. Transaction Annotation In Both Method (Propagation REQUIRED) By Default But Exception In `MessageService`

    - If Exception occur in `MessageService` then rollback will happen, no data will save
    
    - curl --location 'localhost:8080/user/transaction-required-exception'


#### 5. Transaction -> `UserServie` (Propagation REQUIRED) & `MessageService` (Propagation REQUIRED_NEW) 

    - Both will save but `MessageService` will always create new transaction
    
    - curl --location 'localhost:8080/user/transaction-required-new'

#### 6. Transaction -> `UserServie` (Propagation REQUIRED)  & `MessageService` (Propagation REQUIRED_NEW)  With Exception

    - `User Entity` will save but `MessageService` will always create new transaction and occur exception 
       then `UserService` has try catch block to handle `MessageService` method so that `User Entity` will save but `Message Entity` will not save
       if try catch is not there then it will rollback all entities
    
    - curl --location 'localhost:8080/user/transaction-required-new-exception'


#### 7. Transaction -> `UserServie` (Propagation REQUIRED) & `MessageService`  (Propagation SUPPORT) 

    -  if it is directly called from `Controller` to `MessageService` then new transaction will be created
    
    -  if it is called from `UserService` then same transaction will be used
    
    - curl --location 'localhost:8080/user/transaction-support'

#### 8. Transaction -> `UserServie` (Propagation REQUIRED) & `MessageService`  (Propagation MANDATORY)

    -  if it is directly called from `Controller` to `MessageService` then exception occur because there is no transaction
    
    -  if it is called from `UserService` then same transaction will be used
    
    - curl --location 'localhost:8080/user/transaction-mandatory'