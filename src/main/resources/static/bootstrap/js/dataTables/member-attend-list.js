// Call the `s jQuery plugin
$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $( "#testDatepicker" ).datepicker({
        dateFormat: 'yy-mm-dd'
    });

    var table=  $('#dataTable').DataTable(
              {
              "columnDefs": [{
                  "defaultContent": "-",
                  "targets": "_all"
              }],
                  "displayStart": 0,

                  "paging": true,
              "pageLength": 10,
              "pagingType": "full_numbers",
              "aLengthMenu" : [[ 5, 10, 50, -1 ], [ 5, 10, 50, "All" ]],
/*
*/             "searching": false,
                  "processing": true,
              "serverSide": true,
              ajax : {
                      method:"get",
                      url: "/memberAttendHistoryList",
                      beforeSend : function(xhr)
                      {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                          xhr.setRequestHeader(header, token);
                      }
                  },
              columns: [


                  {data: 'memberAttendHisSeq',"visible": false },
                  {data: 'memberName'},
                  {data: 'memberAttendPlace'},
                  {data: 'memberAttendDay'},
                  {data: 'attendCountMonth'},
                  {data: 'lastAttend'},
                  {data: 'regDate'},
                  {data: "memberSeq", "render": function (data, type, row, meta) { return '<a href="' + data + '">자세히 보기</a>';} }
              ],
              dom: 'lBfrtip',
              select: {
                style: 'multi'
              },
              buttons: [
                  {
                      text: '출석 삭제',
                      action: function () {
                          var temp = table.rows('.selected').data();
                          var personArray = new Array();
                         for(var i=0; i < temp.length; i++){
                             var deleteObject = new Object();
                             deleteObject.memberAttendHisSeq = temp[i].memberAttendHisSeq;
                             personArray.push(deleteObject); //list add와 같다.
                         }
                          $.ajax({
                              type : "POST"
                              , url : '/memberAttendHistoryList'
                              ,datatype:"json"
                              ,contentType : "application/json"
                              , data : JSON.stringify(personArray)


                              ,beforeSend : function(xhr)
                              {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                                  xhr.setRequestHeader(header, token);
                              }
                              , error : function(jqXHR, textStatus, errorThrown) { // jqXHR : xml http request object, textStatus : Jquery status code, errorThrown : exception object
                                  /*
                                                       callback(jqXHR.responseJSON, jqXHR);
                                  */
                                  alert(jqXHR.status);
                                  location.reload();

                              }
                              , success : function(data, textStatus, jqXHR){ // data : server response data, textStatus : Jquery status code, jqXHR : xml http request object
                                  /*
                                                       callback(data, jqXHR);
                                  */
                                  alert(jqXHR.status);
                                  location.reload();
                              }
                          });
                      }
                  }
              ]
      });


});