import com.example.moviecatalogue2.data.TvShow

object TvShowData {
    private var data  = arrayOf(
        arrayOf(
            "Pennyworth",
            "81%",
            "July 28, 2019",
            "The origin story of Bruce Wayne's legendary butler, Alfred Pennyworth, a former British SAS soldier who forms a security company and goes to work with Thomas Wayne, Bruce's billionaire father, in 1960s London.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/czVjj5W113Aggz8fmtiW5bY1Vsz.jpg",
            "Bruno Heller"
        ),
        arrayOf(
            "The Boys",
            "81%",
            "July 25, 2019",
            "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/dzOxNbbz1liFzHU1IPvdgUR647b.jpg",
            "Evan Goldberg"
        ),
        arrayOf(
            "Doom Patrol",
            "64%",
            "February 15, 2019",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
            "Jeremy Carver"
        ),
        arrayOf(
            "The Dark Crystal: Age of Resistance",
            "78%",
            "August 30, 2019",
            "Return to the world of Thra, where three Gelfling discover the horrifying secret behind the Skeksis' power and set out to ignite the fires of rebellion and save their world.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rG6bC26xi10i8Mx0ZTyJ7nBkeWv.jpg",
            "Jim Henson"
        ),
        arrayOf(
            "The Spy",
            "73%",
            "September 6, 2019",
            "In the 1960s, Israeli clerk-turned-secret agent Eli Cohen goes deep undercover inside Syria on a perilous, years-long mission to spy for Mossad.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/bCGC6cAEKzm45J7SPsApAnp9mNx.jpg",
            "Gideon Raff"
        ),
        arrayOf(
            "Chernobyl",
            "86%",
            "May 6, 2019",
            "A dramatization of the true story of one of the worst man-made catastrophes in history, the catastrophic nuclear accident at Chernobyl. A tale of the brave men and women who sacrificed to save Europe from unimaginable disaster.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/hlLXt2tOPT6RRnjiUmoxyG1LTFi.jpg",
            "Craig Mazin"
        ),
        arrayOf(
            "Wu-Tang: An American Saga",
            "93%",
            "September 4, 2019",
            "In the early 1990's in New York, during the height of the crack cocaine epidemic, a visionary musician named Bobby Diggs aka The RZA begins to form a super group of a dozen young, black men, who will eventually rise to become one of the unlikeliest success stories in American music history.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/kbZFfquhFg2EOUnKbZedWCs1kWO.jpg",
            "RZA and Alex Tse"
        ),
        arrayOf(
            "The Umbrella Academy",
            "76%",
            "February 15, 2019",
            "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/uYHdIs5O8tiU5p6MvUPd2jElOH6.jpg",
            "Steve Blackman"
        ),
        arrayOf(
            "Another Life",
            "59%",
            "July 25, 2019",
            "After a massive alien artifact lands on Earth, Niko Breckinridge leads an interstellar mission to track down its source and make first contact.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/htazQ0cPoloL5SJ2bqOCKh9UTcc.jpg",
            "Aaron Martin"
        ),
        arrayOf(
            "Vinland Saga",
            "58%",
            "July 8, 2019",
            "For a thousand years, the Vikings have made quite a name and reputation for themselves as the strongest families with a thirst for violence. ",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/vUHlpA5c1NXkds59reY3HMb4Abs.jpg",
            "Shuuhei Yabuta"
        ),
        arrayOf(
            "Swamp Thing",
            "67%",
            "May 31, 2019",
            "CDC researcher Abby Arcane investigates what seems to be a deadly swamp-born virus in a small town in Louisiana but she soon discovers that the swamp holds mystical and terrifying secrets. When unexplainable and chilling horrors emerge from the murky marsh, no one is safe.",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/dD3HcMczLC9wNvfNzx4pZVyl6q8.jpg",
            "Mark Verheiden & Gary Dauberman"
        )

    )

    val listData: ArrayList<TvShow>
        get() {
            val list = ArrayList<TvShow>()
            for (aData in data){
                val tvShow = TvShow()
                tvShow.name = aData[0]
                tvShow.popularity = aData[1]
                tvShow.date = aData[2]
                tvShow.desk = aData[3]
                tvShow.photo = aData[4]
                tvShow.creator = aData[5]

                list.add(tvShow)
            }

            return list
        }
}