# US 010 - Place an order to purchase the property

# 4. Tests 

**Test 1:** Checks if the method of validating if a client already made an order for a property is working correctly - AC3.

    @Test
    void ensure_ClientAlreadyMadeOrderForThisAnnouncement_returnsTrue() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.clientAlreadyMadeOrderForThisAnnouncement(0, email);
        boolean expected = true;

        // Assert
        assertEquals(expected, result);
    }



**Test 2:** Checks if the method of validating if a client already made an order for a property is working correctly - AC3.

	@Test
    void ensure_ClientAlreadyMadeOrderForThisAnnouncement_returnsFalse() {
        // Act
        boolean result = ctrl.clientAlreadyMadeOrderForThisAnnouncement(0, email);
        boolean expected = false;

        // Assert
        assertEquals(expected, result);
    }

**Test 3:**  Checks if the method of validating if someone already made an order for a property with the same amount is working correctly - AC2. 
    
    @Test
    void ensure_SomeoneAlreadyMadeOrderWithSameAmountForThisAnnouncement_returnsTrue() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(100000, 0);
        boolean expected = true;

        // Assert
        assertEquals(expected, result);
    }

**Test 4:** Checks if the method of validating if someone already made an order for a property with the same amount is working correctly - AC2.

    @Test
    void ensure_SomeoneAlreadyMadeOrderWithSameAmountForThisAnnouncement_returnsFalse() {
        // Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(500000, 0, email, d1, OrderState.PENDING);

        // Act
        boolean result = ctrl.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(100000, 0);
        boolean expected = false;

        // Assert
        assertEquals(expected, result);
    }

**Test 5:** Checks if when getting Property Price by Announcement Id, the correct price is returned.

    @Test
    void ensure_GetPropertyPriceByAnnouncementId_returnsCorrectPrice() {
        //Act
        double result = ctrl.getPropertyPriceByAnnouncementId(2);
        double expected = 50000;

        //Assert
        assertEquals(expected, result);

    }

**Test 6:** Checks if the Announcement exists when getting Property Price by Announcement Id.

    @Test
    void ensure_GetPropertyPriceByAnnouncementId_validatesIfAnnouncementExists(){
        //Act
        double result = ctrl.getPropertyPriceByAnnouncementId(5);
        double expected = -1;

        //Assert
        assertEquals(expected, result);
    }

**Test 7:** Checks if the created order was added to the repository.

    @Test
    void ensure_createOrder_addsOrderToRepository() {
        //Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 0, email, d1, OrderState.PENDING);

        //Act
        int result = orderRepo.getSize();
        int expected = 1;

        //Assert
        assertEquals(expected, result);

    }

**Test 8:** When creating an order, checks if the announcement exists.

    @Test
    void ensure_createOrder_validatesIfAnnouncementExists() {
        //Arrange
        Date d1 = new Date(2023, 10, 10);
        ctrl.createOrder(100000, 5, email, d1, OrderState.PENDING);

        //Act
        int result = orderRepo.getSize();
        int expected = 0;

        //Assert
        assertEquals(expected, result);
    }


# 5. Construction (Implementation)


## Class CreateOrderController 

```java
public boolean clientAlreadyMadeOrderForThisAnnouncement(int announcementId, String userEmail) {
        OrderRepository orderRepository = getOrderRepository();
        return orderRepository.clientAlreadyMadeOrderForThisAnnouncement(userEmail, announcementId);
        }
```       
```java        
public boolean someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(double orderAmount, int announcementId) {
        OrderRepository orderRepository = getOrderRepository();
        return orderRepository.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(orderAmount, announcementId);
        }        
```
```java
public double getPropertyPriceByAnnouncementId(int id){
        return getAnnouncementRepository().getPropertyPriceByAnnouncmentId(id);
        }
```
```java
public void createOrder(double orderAmount, int announcementId, String clientEmail, Date date, OrderState orderState){
        OrderRepository orderRepository = getOrderRepository();
        orderRepository.addOrder(orderAmount, announcementId, clientEmail, date, orderState);
        }
```
```java
public String getClientEmail(){
        return getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        }
```

# 6. Integration and Demo



# 7. Observations







