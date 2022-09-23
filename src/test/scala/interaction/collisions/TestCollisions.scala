package interaction.collisions

class TestCollisions extends AnyFunSpec:

    var first:Entity
    var second:Entity

    val entityTypes:List[()=>Entity]=List(
        () => Fish(),
        ()=>Algae(),
        ()=>Food()
    )

    entityTypes.foreach(firstEntityGenerator =>
        entityTypes.foreach(secondEntityGenerator =>
            first = firstEntityGenerator()
            describe("A ${first.class.getName()}") {
                second = secondEntityGenerator()
                describe("and a ${second.class.getName()}") {
                    it("can overlap horizontally") {
                        first.size = (10,10)
                        first.position=(0,0)
                        second.size = (10,10)
                        second.position=(8,8)
                        assert(first.collidesWith(second))
                    }

                    it("can overlap vertically") {
                        //
                    }

                    it("can overlap diagonally") {
                        //
                    }

                    it("can overlap diagonally (reverse)") {
                        //
                    }
                }
            }
        )
    )
