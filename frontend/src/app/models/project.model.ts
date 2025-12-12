export interface Project {
  id?: number;
  title: string;
  description: string;
  fundingGoal: number;
  currentFunding?: number;
  status?: ProjectStatus;
  createdAt?: Date;
  deadline?: Date;
  creator?: {
    id: number;
    username: string;
  };
}

export enum ProjectStatus {
  ACTIVE = 'ACTIVE',
  FUNDED = 'FUNDED',
  CLOSED = 'CLOSED',
  CANCELLED = 'CANCELLED'
}

export interface ProjectRequest {
  title: string;
  description: string;
  fundingGoal: number;
  deadline?: Date;
}
