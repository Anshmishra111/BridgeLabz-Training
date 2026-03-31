package scenariobased;
//Custom Exception
class DuplicateVoteException extends Exception {
 public DuplicateVoteException(String message) {
     super(message);
 }
}

//Abstraction
interface ElectionService {
 void registerVoter(Voter voter);
 void addCandidate(Candidate candidate);
 void castVote(int voterId, int candidateId) throws DuplicateVoteException;
 void showResults();
}

//Voter Class
class Voter {
 private int voterId;
 private String name;
 private boolean hasVoted = false;

 public Voter(int voterId, String name) {
     this.voterId = voterId;
     this.name = name;
 }

 public int getVoterId() {
     return voterId;
 }

 public boolean hasVoted() {
     return hasVoted;
 }

 public void markVoted() {
     hasVoted = true;
 }
}

//Candidate Class
class Candidate {
 private int candidateId;
 private String name;
 private int votes = 0;

 public Candidate(int candidateId, String name) {
     this.candidateId = candidateId;
     this.name = name;
 }

 public int getCandidateId() {
     return candidateId;
 }

 public void addVote() {
     votes++;
 }

 public void displayResult() {
     System.out.println(name + " → Votes: " + votes);
 }
}

//Vote Class (OOP)
class Vote {
 int voterId;
 int candidateId;

 public Vote(int voterId, int candidateId) {
     this.voterId = voterId;
     this.candidateId = candidateId;
 }
}

//Election Implementation
class OnlineElection implements ElectionService {

 private Voter[] voters = new Voter[5];
 private Candidate[] candidates = new Candidate[5];
 private int voterCount = 0;
 private int candidateCount = 0;

 // Register Voter
 public void registerVoter(Voter voter) {
     voters[voterCount++] = voter;
     System.out.println("Voter registered: " + voter.getVoterId());
 }

 // Add Candidate
 public void addCandidate(Candidate candidate) {
     candidates[candidateCount++] = candidate;
     System.out.println("Candidate added: " + candidate.getCandidateId());
 }

 // Cast Vote
 public void castVote(int voterId, int candidateId) throws DuplicateVoteException {
     Voter voter = null;
     Candidate candidate = null;

     for (int i = 0; i < voterCount; i++) {
         if (voters[i].getVoterId() == voterId) {
             voter = voters[i];
             break;
         }
     }

     if (voter == null) {
         System.out.println("Voter not found.");
         return;
     }

     if (voter.hasVoted()) {
         throw new DuplicateVoteException("Duplicate voting not allowed!");
     }

     for (int i = 0; i < candidateCount; i++) {
         if (candidates[i].getCandidateId() == candidateId) {
             candidate = candidates[i];
             break;
         }
     }

     if (candidate == null) {
         System.out.println("Candidate not found.");
         return;
     }

     candidate.addVote();
     voter.markVoted();
     System.out.println("Vote cast successfully!");
 }

 // Show Results
 public void showResults() {
     System.out.println("\n--- Election Results ---");
     for (int i = 0; i < candidateCount; i++) {
         candidates[i].displayResult();
     }
 }
}

//Main Class
public class OnlineVotingSystem {
 public static void main(String[] args) {

     ElectionService election = new OnlineElection();

     // Register Voters
     election.registerVoter(new Voter(1, "Ansh"));
     election.registerVoter(new Voter(2, "Rahul"));

     // Add Candidates
     election.addCandidate(new Candidate(101, "Alice"));
     election.addCandidate(new Candidate(102, "Bob"));

     try {
         // Cast Votes
         election.castVote(1, 101);
         election.castVote(2, 102);
         election.castVote(1, 102); // Duplicate vote
     } catch (DuplicateVoteException e) {
         System.out.println(e.getMessage());
     }

     // Display Results
     election.showResults();
 }
}

