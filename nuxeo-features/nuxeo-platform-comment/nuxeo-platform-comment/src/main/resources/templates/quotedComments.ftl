<p style="margin:0;font-size:13px;">${htmlEscape(principalAuthor.firstName)} says:</p>
<p style="margin:0;font-size:13px;">${comment_text}</p>
<br/>
<#if parentComment.type == "Comment">
  <p style="margin:0;font-size:13px;">It is a reply to the following comment:</p>
  <p style="margin:0;font-size:13px;">${htmlEscape(parentComment.comment.author)} says:</p>
  <p style="margin:0;font-size:13px;">${parentComment.comment.text}</p>
  <br/>
</#if>