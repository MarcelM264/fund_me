import { HttpErrorResponse } from '@angular/common/http';

/**
 * Extracts a user-friendly error message from an HTTP error response.
 * Handles various error types including server errors, network errors, and CORS issues.
 * 
 * @param error - The error object from HttpClient
 * @returns A user-friendly error message string
 */
export function getErrorMessage(error: any): string {
  // Check if this is an HttpErrorResponse (server responded with an error status)
  if (error instanceof HttpErrorResponse) {
    // Server-side error (4xx, 5xx)
    if (error.error) {
      // If the backend returned a string message
      if (typeof error.error === 'string') {
        return error.error;
      }
      // If the backend returned an object with a message property
      if (error.error.message) {
        return error.error.message;
      }
      // If the backend returned an object with an error property
      if (error.error.error) {
        return error.error.error;
      }
    }
    
    // Use status-specific messages
    if (error.status === 0) {
      return 'Unable to connect to the server. Please check your internet connection and ensure the server is running.';
    } else if (error.status === 400) {
      return 'Invalid request. Please check your input and try again.';
    } else if (error.status === 401) {
      return 'Unauthorized. Please check your credentials.';
    } else if (error.status === 403) {
      return 'Access forbidden. You do not have permission to perform this action.';
    } else if (error.status === 404) {
      return 'The requested resource was not found.';
    } else if (error.status === 409) {
      return 'Conflict. The resource already exists.';
    } else if (error.status >= 500) {
      return 'Server error. Please try again later.';
    }
    
    // Generic message with status code
    return `An error occurred (status ${error.status}). Please try again.`;
  }
  
  // Client-side or network error (ProgressEvent, etc.)
  if (error instanceof ErrorEvent) {
    return `Network error: ${error.message}`;
  }
  
  // If error is a string, return it directly
  if (typeof error === 'string') {
    return error;
  }
  
  // Fallback for unknown error types
  return 'An unexpected error occurred. Please try again.';
}

/**
 * Logs detailed error information to the console for debugging.
 * 
 * @param context - A string describing where the error occurred (e.g., 'Signup', 'Login')
 * @param error - The error object to log
 */
export function logError(context: string, error: any): void {
  console.error(`[${context}] Error occurred:`, {
    type: error?.constructor?.name || typeof error,
    status: error?.status,
    statusText: error?.statusText,
    message: error?.message,
    error: error?.error,
    url: error?.url,
    fullError: error
  });
}
