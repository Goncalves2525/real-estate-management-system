# US 011 - As an agent, I want to list real estate purchase orders to accept or decline

# 4. Tests 

**Test 1:** Check if the announcements are sorted by property type and then by id - AC2.

    @Test
    public void testPropertyAndIdCriteriaComparator() {
        // Create test announcements
        Announcement announcement1 = new Announcement(1, "Property A");
        Announcement announcement2 = new Announcement(2, "Property B");
        Announcement announcement3 = new Announcement(3, "Property A");
        Announcement announcement4 = new Announcement(4, "Property C");

        ArrayList<Announcement> announcements = new ArrayList<>(List.of(announcement1, announcement2, announcement3, announcement4));

        // Sort the announcements using the propertyAndIdCriteria comparator
        Comparator<Announcement> propertyAndIdCriteria = new Comparator<Announcement>() {
            @Override
            public int compare(Announcement p1, Announcement p2) {
                return p1.getTypeOfProperty().compareTo(p2.getTypeOfProperty());
            }
        };
        Collections.sort(announcements, propertyAndIdCriteria);

        // Define the expected order after sorting
        ArrayList<Announcement> expectedOrder = new ArrayList<>(List.of(announcement1, announcement3, announcement2, announcement4));

        // Verify that the announcements are sorted correctly
        assertEquals(expectedOrder, announcements);
    }

**Test 2:** Check for each offer if the agent can accept it - AC3.

    @Test
    public void testAcceptOrder() {
        // Arrange
        int idOrder = 1;
        int idAnnouncement = 1;
        Order order = new Order();
        order.setId(idOrder);
        order.setAnnouncementId(idAnnouncement);
        order.setState(OrderState.PENDING);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.getOrders()).thenReturn(orders);

        // Act
        controller.acceptOrder(idOrder, idAnnouncement);

        // Assert
        verify(orderRepository, times(1)).getOrders();
        verify(orderRepository, times(1)).acceptOrder(idOrder, idAnnouncement);

        // Ensure the order state is set to ACCEPTED
        assertSame(OrderState.ACCEPTED, order.getState());
    }


**Test 3:** Check for each offer if the agent can declining it - AC3.

    @Test
    public void testRejectOrder() {
        // Arrange
        int idOrder = 1;
        int idAnnouncement = 1;
        Order order = new Order();
        order.setId(idOrder);
        order.setAnnouncementId(idAnnouncement);
        order.setState(OrderState.PENDING);

        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.getOrders()).thenReturn(orders);

        // Act
        controller.rejectOrder(idOrder, idAnnouncement);

        // Assert
        verify(orderRepository, times(1)).getOrders();
        verify(orderRepository, times(1)).rejectOrder(idOrder, idAnnouncement);

        // Ensure the order state is set to REJECTED
        assertSame(OrderState.REJECTED, order.getState());
    }

# 5. Construction (Implementation)


## Class PropertyOrderManagementController

```java
    public ArrayList<Announcement> getAllAnnouncementsSortedByproperty() {
        return getAnnouncementRepository().getgetAllAnnouncementsSortedByproperty();
        }
```

```java   
   public ArrayList<Order> getOrdersListByAnnouncementId(Announcementid) {
        return getOrderRepository().getOrdersByAnnouncementId(id);
    }   
``` 

```java   
    public void acceptOrder(int idOrder, int Announcementid) {
        getOrderRepository().acceptOrder(idOrder,idAnnouncement);
    }       
```
```java   
    public void rejectOrder(int idOrder, int Announcementid) {
        getOrderRepository().rejectOrder(idOrder,idAnnouncement);
    }       
```

# 6. Integration and Demo 


# 7. Observations






