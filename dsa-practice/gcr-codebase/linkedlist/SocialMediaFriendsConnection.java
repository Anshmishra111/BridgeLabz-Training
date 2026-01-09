package linkedlist;
public class SocialMediaFriendsConnection {
    class FriendNode {
        int friendId;
        FriendNode next;

        FriendNode(int friendId) {
            this.friendId = friendId;
            this.next = null;
        }
    }

    class UserNode {
        int userId;
        String name;
        int age;
        FriendNode friends; // Head of friend list
        UserNode next;

        UserNode(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            this.friends = null;
            this.next = null;
        }
    }

    UserNode head = null;

    // -------- Add User --------
    void addUser(int id, String name, int age) {
        UserNode newUser = new UserNode(id, name, age);
        newUser.next = head;
        head = newUser;
    }

    // -------- Search User by ID --------
    UserNode getUserById(int id) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == id)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    // -------- Search User by Name --------
    void searchByName(String name) {
        UserNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                displayUser(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("User not found.");
    }

    // -------- Add Friend Connection --------
    void addFriend(int userId, int friendId) {
        UserNode user = getUserById(userId);
        UserNode friend = getUserById(friendId);

        if (user == null || friend == null) {
            System.out.println("Invalid user ID.");
            return;
        }

        user.friends = addFriendToList(user.friends, friendId);
        friend.friends = addFriendToList(friend.friends, userId);

        System.out.println("Friend connection added.");
    }

    FriendNode addFriendToList(FriendNode head, int id) {
        FriendNode newNode = new FriendNode(id);
        newNode.next = head;
        return newNode;
    }

    // -------- Remove Friend Connection --------
    void removeFriend(int userId, int friendId) {
        UserNode user = getUserById(userId);
        UserNode friend = getUserById(friendId);

        if (user == null || friend == null) {
            System.out.println("Invalid user ID.");
            return;
        }

        user.friends = removeFriendFromList(user.friends, friendId);
        friend.friends = removeFriendFromList(friend.friends, userId);

        System.out.println("Friend connection removed.");
    }

    FriendNode removeFriendFromList(FriendNode head, int id) {
        if (head == null) return null;

        if (head.friendId == id)
            return head.next;

        FriendNode temp = head;
        while (temp.next != null && temp.next.friendId != id) {
            temp = temp.next;
        }

        if (temp.next != null)
            temp.next = temp.next.next;

        return head;
    }

    // -------- Display Friends of a User --------
    void displayFriends(int userId) {
        UserNode user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("Friends of " + user.name + ": ");
        FriendNode temp = user.friends;

        if (temp == null) {
            System.out.println("No friends.");
            return;
        }

        while (temp != null) {
            System.out.print(temp.friendId + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // -------- Find Mutual Friends --------
    void findMutualFriends(int user1, int user2) {
        UserNode u1 = getUserById(user1);
        UserNode u2 = getUserById(user2);

        if (u1 == null || u2 == null) {
            System.out.println("Invalid user ID.");
            return;
        }

        System.out.print("Mutual Friends: ");
        FriendNode f1 = u1.friends;
        boolean found = false;

        while (f1 != null) {
            FriendNode f2 = u2.friends;
            while (f2 != null) {
                if (f1.friendId == f2.friendId) {
                    System.out.print(f1.friendId + " ");
                    found = true;
                }
                f2 = f2.next;
            }
            f1 = f1.next;
        }

        if (!found)
            System.out.print("None");

        System.out.println();
    }

    // -------- Count Friends for Each User --------
    void countFriends() {
        UserNode temp = head;

        while (temp != null) {
            int count = 0;
            FriendNode f = temp.friends;
            while (f != null) {
                count++;
                f = f.next;
            }
            System.out.println(temp.name + " has " + count + " friends.");
            temp = temp.next;
        }
    }

    // -------- Display User --------
    void displayUser(UserNode u) {
        System.out.println("ID: " + u.userId +
                ", Name: " + u.name +
                ", Age: " + u.age);
    }

    // -------- Main --------
    public static void main(String[] args) {
    	SocialMediaFriendsConnection sm = new SocialMediaFriendsConnection();

        sm.addUser(1, "Ansh", 22);
        sm.addUser(2, "Ravi", 23);
        sm.addUser(3, "Aman", 21);
        sm.addUser(4, "Neha", 22);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);
        sm.addFriend(2, 4);

        sm.displayFriends(1);
        sm.findMutualFriends(1, 2);

        sm.searchByName("Neha");
        sm.countFriends();

        sm.removeFriend(1, 3);
        sm.displayFriends(1);
    }
}

