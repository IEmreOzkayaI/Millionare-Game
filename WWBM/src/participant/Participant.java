package participant;

import java.util.Random;

import list.ParticipantList;

public class Participant {

	private int participantId;
	private String name;
	private Birthdate birthDate;
	private PhoneNumber phone;
	private Address address;

	ParticipantList participantList = new ParticipantList(); // it creates special dynamic array.

	public Participant() {

	}

	public Participant(int participantId, String name, Birthdate birthDate, String phone, String address) {
		this.participantId=participantId;
		this.name = name;
		this.birthDate = birthDate;
		this.phone = new PhoneNumber(phone);
		this.address = new Address(address);
	}

	public void setParticipantId(int id) {
		this.participantId = id;
	}

	public int getParticipantId() {
		return participantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Birthdate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Birthdate birthDate) {
		this.birthDate = birthDate;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void add(String[] participants) { // set participant's futures.
		for (int i = 0; i < participants.length; i++) {
			Participant participant = new Participant(); // create new object on the each step.
			String[] word = participants[i].split("#");

			participant.setParticipantId(i + 1);
			participant.setName(word[0]);
			participant.setBirthDate(new Birthdate(word[1]));
			participant.setPhone(new PhoneNumber(word[2]));
			participant.setAddress(new Address(word[3]));
			participantList.Add(participant);
		}
		System.out.println(" The file is loaded.\n");
	}

	public Participant[] getParticipants() { // return all participants
		return participantList.getList();
	}

	public Participant getRandomParticipant() { // it generates random number for getting random participant from array.
		Random random = new Random();
		int randomIndex = random.nextInt(participantList.getList().length);
		Participant[] participant = participantList.getList();
		return participant[randomIndex];
	}

}
