<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap");

      .kmButton {
      font-weight: bold;
      line-height: 100%;
      text-align: center;
      text-decoration: none;
      color: white;
      font-family: Helvetica, Arial;
      font-size: 16px;
    }
      
  </style>
  <body>
    
    <div style="font-family: 'Montserrat', sans-serif; align-items: center; justify-content: space-between;">
        <div style="width: 500px; background-color: #fff; border-radius: 25rem; box-shadow: 0 0.5em 1em -0.125em rgb(10 10 10 / 10%), 0 0 0 1px rgb((10 10 10 / 2%), green, blue); color: #4a4a4a; max-width: 100%; position: relative;">
          <div style="display: block; position: relative; border-top-left-radius: .25rem; border-top-right-radius: .25rem;">
              <figure style="padding-top: 50%;">
                <img style = "width: auto; height: 300px;" src="https://cdn.formidableforms.com/wp-content/uploads/2017/12/confirmation-emails-are-expected-by-customers.png" alt="Placeholder image">
              </figure>
          </div>
            <div style="background-color: transparent; padding: 1.5rem">
                <p style="align-items: center; display: flex; flex-basis: auto; flex-grow: 0; flex-shrink: 0; justify-content: center; color: #363636; font-size: 2rem; font-weight: 600; line-height: 1.125; text-align: center !important;">
                   Verifica Account <br/>
                  <p style="color: #4a4a4a; font-size: 1.25rem; font-weight: 400; line-height: 1.25; word-break: word-break; margin-top: .5rem !important;">
                    Benvenuto/a ${name}, ci sei quasi! Clicca qui sotto per confermare il tuo account.
                  </p>
                <p style="align-items: center; display: flex; flex-basis: auto; flex-grow: 0; flex-shrink: 0; justify-content: center; text-align: center !important;">
                    <!--<a href="${link}"><img style = "width: 150px; height: auto;" src="https://thumbs.dreamstime.com/b/confirm-213158609.jpg"></a>-->
                    <a class="kmButton" href="${link}" style="
                                                      text-decoration: none;
                                                      display: inline-block;
                                                      background-color: orangered;
                                                      font-size: 16px;
                                                      font-weight: bold;
                                                      letter-spacing: 0px;
                                                      padding-top: 15px;
                                                      padding-bottom: 15px;
                                                      font-family: 'Montserrat',
                                                      'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                                                      padding-left: 15px;
                                                      padding-right: 15px; ;
                                                    " target="_self" title="">Conferma il tuo account</a>
                </p>
            </div>
            <div style="font-family: 'Montserrat', sans-serif; font-size: x-small; text-align: center !important; display: flex; flex-basis: auto; flex-grow: 0; flex-shrink: 0; justify-content: center; background-color: transparent; border-top: 1px solid #ededed;">
                Email inviata da MyProfile App<br/><br/>
                Copyright © 2021 Tutti i diritti riservati.
                E' proibita la riproduzione, anche parziale, in ogni forma o mezzo, senza espresso permesso scritto
                dell'autore.
            </div>
    </div>
  </body>
</html>