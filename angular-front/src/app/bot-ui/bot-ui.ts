import { Component, inject } from '@angular/core';
import { HttpClient, HttpDownloadProgressEvent, HttpEventType } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MarkdownComponent } from 'ngx-markdown';
import { LoadingService } from '../services/loading';

@Component({
  imports: [AsyncPipe, FormsModule, MarkdownComponent],
  selector: 'app-bot-ui',
  styleUrl: './bot-ui.css',
  templateUrl: './bot-ui.html',
})
export class BotUi {
  public response$!: Observable<any>;
  public query: any;
  public http = inject(HttpClient);
  public loadingService = inject(LoadingService);

  protected askAgent() {
    console.log(this.query);
    this.response$ = this.http.get(
      'http://localhost:9999/EBANKMCPCLIENT/chat?query=' + this.query,
      { responseType: 'text' },
    );
  }

  protected askAgentStream() {
    console.log(this.query);
    this.response$ = this.http.get(
      'http://localhost:9999/EBANKMCPCLIENT/chatstream?query=' + this.query,
      { responseType: 'text' , observe : 'events', reportProgress:true}).pipe(
        map(event => {
          switch (event.type) {
            case HttpEventType.Sent:
               return {'type': 'sent'}
            case HttpEventType.DownloadProgress:
              return { type: 'Response', content : (event as HttpDownloadProgressEvent).partialText };
            case HttpEventType.Response:
              return { type: 'Response', content: event.body };
            default : return { type : 'other', data:event}
          }
        })
    )
  }
}
