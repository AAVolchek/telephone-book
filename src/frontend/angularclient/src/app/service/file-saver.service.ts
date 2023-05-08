import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileSaverService {

  saveAs(blob: Blob, filename: string) {
    const url = window.URL.createObjectURL(blob);
    const anchor = document.createElement('a');
    anchor.download = filename;
    anchor.href = url;
    anchor.click();
  }

}
