package inheritance;

	interface Worker {
	    void performDuties();
	}

	class RestaurantPerson {
	    String name;
	    int id;

	    RestaurantPerson(String name, int id) {
	        this.name = name;
	        this.id = id;
	    }
	}

	class Chef extends RestaurantPerson implements Worker {
	    Chef(String name, int id) {
	        super(name, id);
	    }

	    public void performDuties() {
	        System.out.println("Cooking food.");
	    }
	}

	class Waiter extends RestaurantPerson implements Worker {
	    Waiter(String name, int id) {
	        super(name, id);
	    }

	    public void performDuties() {
	        System.out.println("Serving customers.");
	    }
	}

