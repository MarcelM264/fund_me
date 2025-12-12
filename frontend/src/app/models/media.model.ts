export interface Media {
  id?: number;
  fileName: string;
  fileType: string;
  mediaType: MediaType;
  url: string;
  uploadedAt?: Date;
}

export enum MediaType {
  VIDEO = 'VIDEO',
  IMAGE = 'IMAGE'
}
