import { Session } from "./session";

export interface User {
    id: string,
    username: string,
    password: string,
    sessions: Session[] 
}
