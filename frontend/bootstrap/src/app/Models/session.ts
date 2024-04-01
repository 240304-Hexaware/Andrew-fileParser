import { Rmetadata } from "./rmetadata";

export interface Session {
    id: string | undefined,
    numDownloaded: number,
    records: Object[],
    rmetadata: Rmetadata
}
