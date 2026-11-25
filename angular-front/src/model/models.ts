export interface account {
  id?: String,
  balance? : number,
  createdAt? : String,
  type? : String,
  customerId? : String
}

export enum RequestStatus{
  SUCCESS, ERROR
}

export interface accountListState {
  accounts? : account[],
  status? : RequestStatus,
  errorMessage? : String
}
