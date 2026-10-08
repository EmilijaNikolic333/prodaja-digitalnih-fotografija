# Prodaja digitalnih fotografija

Desktop aplikacija tipa klijent–server za praćenje prodaje digitalnih fotografija. Seminarski rad iz predmeta **Projektovanje softvera** na Fakultetu organizacionih nauka u Beogradu.

Umetnik se prijavljuje u sistem i vodi evidenciju o računima, kupcima, umetnicima, digitalnim fotografijama, mestima i stručnim spremama.

## Tehnologije

- Java (Swing) – klijentska i serverska aplikacija
- Socket komunikacija klijent–server, višenitni server
- MySQL / MariaDB, JDBC
- NetBeans (Ant)
- Arhitektura po Larmanovoj metodi: generičke sistemske operacije i generički broker baze podataka

## Struktura

| Projekat | Opis |
|---|---|
| `seminarski` | Zajednički projekat: domenske klase i klase za komunikaciju (`Zahtev`, `Odgovor`, `Operacija`…) |
| `server_seminarski` | Server: forme za server i konfiguraciju baze, kontroler, sistemske operacije, rad sa bazom |
| `klijent_seminarski` | Klijent: login, glavna forma, forme i kontroleri za sve domenske objekte |
| `baza.sql` | Skripta za kreiranje baze sa test podacima |

## Pokretanje

1. Pokrenite MySQL (npr. preko XAMPP-a) i importujte `baza.sql`. Napraviće se baza `prodajafotografija`.
2. U NetBeans-u otvorite sva tri projekta.
3. Na projektu `seminarski` uradite **Clean and Build**.
4. U projektima `server_seminarski` i `klijent_seminarski` otvorite **Properties → Libraries** i dodajte projekat `seminarski`. Serveru dodajte i MySQL JDBC drajver ([MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)).
5. Proverite podatke za bazu u `server_seminarski/config/config.properties`. Podrazumevano je `localhost:3306`, korisnik `root`, bez lozinke, a server radi na portu `9000`.
6. Pokrenite `server_seminarski` i kliknite **POKRENI SERVER**.
7. Pokrenite `klijent_seminarski` i prijavite se sa korisničkim imenom **demo** i šifrom **demo**.

Svi podaci u bazi su izmišljeni i služe samo za testiranje.

## Autor

Emilija Nikolić – [GitHub](https://github.com/EmilijaNikolic333)
