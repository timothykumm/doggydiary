import { Injectable } from '@angular/core';
import { PSM, OEM, Worker } from 'tesseract.js';

@Injectable({
  providedIn: 'root'
})
export class TensorflowService {

  constructor() { }

  extractText = async (worker: Worker, file: File) => {
    await worker.load()
    await worker.loadLanguage('deu')
    await worker.initialize('deu', OEM.LSTM_ONLY)

    await worker.setParameters({
      tessedit_pageseg_mode: PSM.SINGLE_BLOCK,
    })

    const { data } = await worker.recognize(file)

    return data.text
  }

}
