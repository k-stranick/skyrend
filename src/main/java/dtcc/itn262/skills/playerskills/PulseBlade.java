package dtcc.itn262.skills.playerskills;

import dtcc.itn262.character.Player;
import dtcc.itn262.monster.generic.Monster;


public class PulseBlade implements PlayerSkill {
        // damage for kills is based off a multiplier of the player's magic attribute shold this be a concrete value??
// should i display all skill stats up top?
        private static final int MANA_COST = 33;
        private int currentCooldown = 0;


        /**
         * @return skillName the name of the skill
         */
        @Override
        public String getSkillName() {
            return "Test Skill 2";
        }


        /**
         * @return manaCost the cost of the skill
         */
        @Override
        public int getManaCost() {
            return MANA_COST;
        }


        /**
         * @return
         */
        @Override
        public boolean isOnCooldown() {
            return currentCooldown > 0;
        }


        @Override
        public void reduceCooldown() {
            if (currentCooldown > 0) {
                currentCooldown--;
            }

        }


        /**
         *
         */
        @Override
        public void setCooldown() {
            // cooldown in turns
            currentCooldown = 3;

        }


        /**
         * @param player
         * @param target
         */
        @Override
        public void useSkill(Player player, Monster target) {
            double damageMultiplier = 1.5;
            double damage = (player.getPlayerAttributes().getMagic() * damageMultiplier) - target.getMonsterAttributes().getMagicDefense();
            if (isOnCooldown()) {
                System.out.println(getSkillName() + "is on cooldown");

            } else if (player.getPlayerAttributes().getMana() < getManaCost()) {
                System.out.println("Not enough mana to use " + getSkillName());

            } else {
                player.getPlayerAttributes().setMana(player.getPlayerAttributes().getMana() - MANA_COST); // reduces mana

                if (damage < 0) { // if damage is negative, set damage to 0 and print that the target blocked the skill
                    // ADD && type != getMonsterAttributes().getType()
                    // then in next else if block put logic for spell type and monster type the damage in the else statement will
                    // add the negative damage to the target's health
                    damage = 0; // explicitly set damage to 0
                    System.out.println(target.getMonster() + " blocked " + getSkillName() + ".");
                } else {
                    target.getMonsterAttributes().setHealth((int) Math.round(target.getMonsterAttributes().getHealth() - damage)); // deals damage
                    System.out.println(player.getHero() + " uses " + getSkillName() + " on " + target.getMonster() + " for " + damage + " damage.");
                }
                setCooldown();
            }
        }


        /**
         * @return
         */
        @Override
        public int getCurrentCooldown() {  // should return current cooldown status of skill
            return currentCooldown;
        }
    }

