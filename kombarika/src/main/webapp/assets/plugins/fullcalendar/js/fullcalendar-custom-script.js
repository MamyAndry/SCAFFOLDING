$(document).ready(function() {



  $('#calendar').fullCalendar({

    header: {

      left: 'prev,next today',

      center: 'title',

      right: 'month,agendaWeek,agendaDay'

    },

    defaultDate: '2018-03-12',

    navLinks: true, // can click day/week names to navigate views

    selectable: true,

    selectHelper: true,





    editable: true,

    eventLimit: true, // allow "more" link when too many events

    events: [

      {

        title: 'All Day Event',

        start: '2018-03-01'

      },

      {

        title: 'Long Event',

        start: '2018-03-07',

        end: '2018-03-10'

      },

      {

        id: 999,

        title: 'Repeating Event',

        start: '2018-03-09T16:00:00'

      },

      {

        id: 999,

        title: 'Repeating Event',

        start: '2018-03-16T16:00:00'

      },

      {

        title: 'Conference',

        start: '2018-03-11',

        end: '2018-03-13'

      },

      {

        title: 'Meeting',

        start: '2018-03-12T10:30:00',

        end: '2018-03-12T12:30:00'

      },

      {

        title: 'Lunch',

        start: '2018-03-12T12:00:00'

      },

      {

        title: 'Meeting',

        start: '2018-03-12T14:30:00'

      },

      {

        title: 'Happy Hour',

        start: '2018-03-12T17:30:00'

      },

      {

        title: 'Dinner',

        start: '2018-03-12T20:00:00'

      },

      {

        title: 'Birthday Party',

        start: '2018-03-13T07:00:00'

      },

      {

        title: 'Click for Google',

        url: 'http://google.com/',

        start: '2018-03-28'

      }

    ],
    select: function(start, end) {
      console.log(parseDateString(start._d.toLocaleDateString()))
      var enventDetails = document.getElementById('eventDetails');
      // enventDetails.appendChild()
      $('#eventDetails').html('<p>' + start._d.toLocaleDateString() + '</p><p>' + end._d.toLocaleDateString() + '</p>');
      // var date = document.getElementById('debut');
      date.value = parseDateString(start._d.toLocaleDateString()) ;

      $('#myModal').modal('show');
    }
    var title = prompt('Event Title:');

        var eventData;

        if (title) {

          eventData = {

            title: title,

            start: start,

            end: end

          };

          $('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true

        }

        $('#calendar').fullCalendar('unselect');

  });

});

window.addEventListener("load", () => {

  let map;
  var form = document.getElementById("form");
  let marker;

  async function initMap() {
      const position = { lat: -18.9162618, lng: 47.5128566 };
      const { Map } = await google.maps.importLibrary("maps");

      map = new Map(document.getElementById("map"), {
          zoom: 12,
          center: position,
          mapId: "DEMO_MAP_ID",
      });

      map.addListener('click', (e) => {
          placeMarker(e.latLng);
      });
  }


  function placeMarker(position) {
      if (marker) {
          marker.setPosition(position);
      } else {
          marker = new google.maps.Marker({
          position: position,
          map: map
          });
      }
  }

  function sendData() {
      var xhr;

      try {  xhr = new ActiveXObject('Msxml2.XMLHTTP');   }
      catch (e)
      {
          try {   xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
          catch (e2)
          {
              try {  xhr = new XMLHttpRequest();  }
              catch (e3) {  xhr = false;   }
          }
      }

      // Liez l'objet FormData et l'élément form
      var formData = new FormData(form);

      formData.append("longitude", marker.getPosition().lng());
      formData.append("latitude", marker.getPosition().lat());

      // Définissez ce qui se passe si la soumission s'est opérée avec succès
      xhr.addEventListener("load", (event) => {
          let msg = (event.target.responseText != "") ? event.target.responseText : "La mission a été ajoutée";
          alert(msg);
      });

      // Definissez ce qui se passe en cas d'erreur
      xhr.addEventListener("error", (event) => {
          alert('Oups! Quelque chose s\'est mal passé.');
      });

      // Configurez la requête
      xhr.open("POST", "mission");

      // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
      xhr.send(formData);
  }

  form.addEventListener("submit", (event) => {
      event.preventDefault(); // évite de faire le submit par défaut

      sendData();
  });

  initMap();

});

function parseDateString(dateString) {
  // Create a new Date object from the dateString
  var date = new Date(dateString);

  // Extract year, month, and day components
  var year = date.getFullYear();
  var month = ('0' + (date.getMonth() + 1)).slice(-2); // Months are zero-indexed, so we add 1
  var day = ('0' + date.getDate()).slice(-2);

  // Format the components into YYYY-MM-DD format
  var formattedDate = year + '-' + month + '-' + day;

  return formattedDate;
}
