export interface Funding {
  id?: number;
  amount: number;
  message?: string;
  fundedAt?: Date;
  supporter?: {
    id: number;
    username: string;
  };
  project?: {
    id: number;
    title: string;
  };
}

export interface FundingRequest {
  amount: number;
  message?: string;
}
