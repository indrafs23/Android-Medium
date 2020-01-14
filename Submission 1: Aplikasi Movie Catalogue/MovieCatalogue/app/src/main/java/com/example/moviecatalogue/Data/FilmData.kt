package com.example.moviecatalogue.Data

object FilmData {
    private var data  = arrayOf(
        arrayOf(
            "Avengers: Endgame",
            "84%",
            "April 24, 2019",
            "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
            "https://upload.wikimedia.org/wikipedia/en/0/0d/Avengers_Endgame_poster.jpg",
            "Joe Russo & Anthony Russo"
        ),
        arrayOf(
            "The Lion King",
            "72%",
            "July 12, 2019",
            "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
            "https://upload.wikimedia.org/wikipedia/en/9/9d/Disney_The_Lion_King_2019.jpg",
            "Jon Favreau"
        ),
        arrayOf(
            "Captain Marvel",
            "70%",
            "March 6, 2019",
            "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
            "https://upload.wikimedia.org/wikipedia/en/8/85/Captain_Marvel_poster.jpg",
            "Anna Boden & Ryan Fleck"
        ),
        arrayOf(
            "Spider-Man: Far From Home",
            "77%",
            "June 28, 2019",
            "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
            "https://upload.wikimedia.org/wikipedia/en/b/bd/Spider-Man_Far_From_Home_poster.jpg",
            "Jon Watts"
        ),
        arrayOf(
            "Aladdin",
            "71%",
            "May 22, 2019",
            "A kindhearted street urchin named Aladdin embarks on a magical adventure after finding a lamp that releases a wisecracking genie while a power-hungry Grand Vizier vies for the same lamp that has the power to make their deepest wishes come true.",
            "https://upload.wikimedia.org/wikipedia/en/9/9a/Aladdin_%28Official_2019_Film_Poster%29.png",
            "Guy Ritchie"
        ),
        arrayOf(
            "Toy Story 4",
            "77%",
            "October 30, 1995",
            "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
            "https://upload.wikimedia.org/wikipedia/en/4/4c/Toy_Story_4_poster.jpg",
            "Josh Cooley"
        ),
        arrayOf(
            "The Wandering Earth",
            "63%",
            "February 5, 2019",
            "When the Sun begins to expand in such a way that it will inevitably engulf and destroy the Earth in a hundred years, united mankind finds a way to avoid extinction by propelling the planet out of the Solar System using gigantic engines, moving it to a new home located four light years away, an epic journey that will last thousands of years.",
            "https://upload.wikimedia.org/wikipedia/en/1/1b/The_Wandering_Earth_film_poster.jpg",
            "Frant Gwo"
        ),
        arrayOf(
            "Ne Zha",
            "80%",
            "July 26, 2019",
            "The Primus extracts a Mixed Yuan Bead into a Spirit Seed and a Demon Pill. The Spirit Seed can be reincarnated as a human to help King Zhou establish a new dynasty, whereas the Demon Pill will create a devil threatening humanity. Ne Zha is the one who is destined to be the hero, but instead he becomes a devil incarnate, because the Spirit Seed and a Demon Pill are switched.",
            "https://upload.wikimedia.org/wikipedia/en/a/a2/Nezha_film_poster.jpg",
            "Yu Yang"
        ),
        arrayOf(
            "How to Train Your Dragon: The Hidden World",
            "77%",
            "January 3, 2019",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            "https://upload.wikimedia.org/wikipedia/en/f/fd/How_to_Train_Your_Dragon_3_poster.png",
            "Dean DeBlois"
        ),
        arrayOf(
            "Fast & Furious Presents: Hobbs & Shaw",
            "65%",
            "August 1, 2019",
            "A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",
            "https://upload.wikimedia.org/wikipedia/en/0/00/Fast_%26_Furious_Presents_Hobbs_%26_Shaw_-_theatrical_poster.jpg",
            "David Leitch"
        ),
        arrayOf(
            "John Wick: Chapter 3 - Parabellum",
            "71%",
            "May 15, 2019",
            "Super-assassin John Wick returns with a \$14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
            "https://upload.wikimedia.org/wikipedia/id/9/94/John_Wick_Chapter_3_Parabellum.png",
            "Chad Stahelski"
        )

    )

    val listData: ArrayList<Film>
        get() {
            val list = ArrayList<Film>()
            for (aData in data){
                val film = Film()
                film.name = aData[0]
                film.popularity = aData[1]
                film.date = aData[2]
                film.desk = aData[3]
                film.photo = aData[4]
                film.director = aData[5]

                list.add(film)
            }

            return list
        }
}