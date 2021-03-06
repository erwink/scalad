package scalad.example.jpa

import scalad.example.User
import javax.persistence.{EntityManager, Persistence}
import scalad.transaction.Transactions
import scalad.jpa.JPA

/**
 * @author janmachacek
 */
object Main {

  import scalad._
  import Scalad._

  def main(args: Array[String]) {
    val entityManagerFactory = Persistence.createEntityManagerFactory("org.cakesolutions.scaladata.core.example")
    val entityManager = entityManagerFactory.createEntityManager()
    new Worker(entityManager).work()
  }

  class Worker(entityManager: EntityManager) extends JPA(entityManager) with Transactions {
    
    def getUsers = select(list[User])

    def work() {
      for (i <- 0 to 20) {
        val u = new User()
        u.setUsername("a" + i)
        u.persist
      }

      val users = select(list[User])
      println(users)

//      val f = sel(list[User])
//      val b = f | ("username" like "a%") |
//      println(b)

//      val m1 = head[User] >>= (u => head map (u2 => (u <|*|> u2)))
//      val firstTwo = selector(m1)
//      println(firstTwo("username" like "a2%" && "firstName" like "a1%"))
//
//      select(one[User])
//      selectThat(one[User])("username" like "B")
//
//      val usersWhose = selectThat(head[User])("username" like "a1%")
//      println(usersWhose)
    }

  }

}