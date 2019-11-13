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
              "order": [[ 2, "asc" ]],
              "paging": true,
              "ordering": true,
              "searching": true,
              ajax : {
                      method:"get",
                      url: "/memberList",
                      beforeSend : function(xhr)
                      {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                          xhr.setRequestHeader(header, token);
                      }
                  },
              columns: [

/*
                  {data: 'memberSeq',"visible": false },
*/
                  {data: 'memberName'},
                  {data: 'memberNickName'},
                  {data: 'somoimJoinDate'},
                  {data: 'attendCount'},
                  {data: 'attendCountMonth'},
                  {data: 'lastAttend'},
                  {data: "memberSeq", "render": function (data, type, row, meta) { return '<a href="' + data + '">자세히 보기</a>';} }
              ],
              dom: 'Bfrtip',
              select: {
                style: 'multi'
              },
              buttons: [
                  {
                      text: '회원 삭제',
                      action: function () {
                          var selectedData = table.rows('.selected').any();
                          if(selectedData == false) {
                              alert("회원을 선택 해주세요.");
                              return false;
                          }
                          var temp = table.rows('.selected').data();
                          var personArray = new Array();
                         for(var i=0; i < temp.length; i++){
                             var deleteObject = new Object();
                             deleteObject.memberSeq = temp[i].memberSeq;
                             personArray.push(deleteObject); //list add와 같다.
                         }

                          $.ajax({
                              type : "POST"
                              , url : '/memberList'
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
                  },
                  //add
                  {
                      text: '출석',
                      action: function () {
                          var selectedData = table.rows('.selected').any();
                          if(selectedData == false){
                           alert("회원을 선택 해주세요.");
                           $('#alert').show();
                           return false;
                          }

                          $("#myModal").modal();
                      }
                  }
              ]
      });

    $("#saveAttend").on("click",function () {

        var temp = table.rows('.selected').data();
        var personArray = new Array();
        for(var i=0; i < temp.length; i++){
            var memberObject = new Object();
            memberObject.memberSeq = temp[i].memberSeq;
            memberObject.attendCount = temp[i].attendCount;
            memberObject.attendCountMonth = temp[i].attendCountMonth;
            personArray.push(memberObject); //list add와 같다.
        }
        var attendObject = new Object();
        attendObject.memberAttendDate = $('#testDatepicker').val();
        attendObject.memberAttend = personArray;
        attendObject.memberAttendPlace = $('#moimPlace').val();
        $.ajax({
            type : "POST"
            , url : '/attend'
            ,datatype:"json"
            ,contentType : "application/json"
            , data : JSON.stringify(attendObject)

            ,beforeSend : function(xhr)
            {   //데이터를 전송하기 전에 헤더에 csrf값을 설정한다
                xhr.setRequestHeader(header, token);
            }
            , error : function(jqXHR, textStatus, errorThrown) { // jqXHR : xml http request object, textStatus : Jquery status code, errorThrown : exception object

/*
                callback(jqXHR.responseJSON, jqXHR);
*/
                console.log("oxox");

                alert(jqXHR.status);
                location.reload();
            }
            , success : function(data, textStatus, jqXHR){ // data : server response data, textStatus : Jquery status code, jqXHR : xml http request object

/*
                callback(data, jqXHR);
*/                console.log("xoxo");

                alert(jqXHR.status);
                location.reload();
            }
        });
    });









});