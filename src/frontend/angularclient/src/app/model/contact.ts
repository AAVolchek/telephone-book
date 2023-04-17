export class Contact {

  id: string;
  firstName : string;
  lastName : string;
  phoneNumber : string;
  email : string;
  birthday : Date;
  socialProfile : string;

  constructor(firstName: string, lastName: string, phoneNumber: string, email: string, birthday: Date, socialProfile: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.birthday = birthday;
    this.socialProfile = socialProfile;
  }
}
