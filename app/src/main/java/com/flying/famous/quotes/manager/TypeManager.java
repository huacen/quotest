package com.flying.famous.quotes.manager;

import com.flying.famous.quotes.R;
import com.flying.famous.quotes.db.DBManager;
import com.flying.famous.quotes.db.entity.Type;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TypeManager {
    private Map<Long, Type> typeMap = new ConcurrentHashMap<>();
    private Map<String, TypeConfig> configMap = new HashMap<>();


    private static class Inner {
        private static final TypeManager INSTANCE = new TypeManager();
    }

    public static TypeManager getInstance() {
        return Inner.INSTANCE;
    }

    private TypeManager() {
        initDefaultConfig();
    }

    public void init() {
        List<Type> types = DBManager.INSTANCE().getType().loadAll();
        if (types != null && types.size() > 0) {
            for (Type type : types) {
                typeMap.put(type.getId(), type);
            }
        }

        initDefaultConfig();
    }

    public Type getType(long id) {
        return typeMap.get(id);
    }

    public Collection getAllTypes() {
        return typeMap.values();
    }

    public TypeConfig getConfig(String name) {
        return configMap.get(name);
    }

    private void initDefaultConfig() {
        if (configMap.size() > 0) {
            return;
        }
        addTypeConfig(new TypeConfig("Alone", R.drawable.alone, "#46BF9A"));
        addTypeConfig(new TypeConfig("Amazing", R.drawable.amazing, "#4500E9"));
        addTypeConfig(new TypeConfig("Anger", R.drawable.anger, "#951B36"));
        addTypeConfig(new TypeConfig("Anniversary", R.drawable.anniversary, "#FF89BF"));
        addTypeConfig(new TypeConfig("Architecture", R.drawable.architecture, "#BB5D4C"));
        addTypeConfig(new TypeConfig("Art", R.drawable.art, "#F78E36"));
        addTypeConfig(new TypeConfig("Attitude", R.drawable.attitude, "#3584AD"));
        addTypeConfig(new TypeConfig("Age", R.drawable.age, "#94347A"));


        addTypeConfig(new TypeConfig("Beauty", R.drawable.beauty, "#E85D49"));
        addTypeConfig(new TypeConfig("Best", R.drawable.best, "#34A633"));
        addTypeConfig(new TypeConfig("Birthday", R.drawable.birthday, "#CF5058"));
        addTypeConfig(new TypeConfig("Brainy", R.drawable.brainy, "#309A45"));
        addTypeConfig(new TypeConfig("Business", R.drawable.business, "#33995F"));


        addTypeConfig(new TypeConfig("Car", R.drawable.car, "#5654C4"));
        addTypeConfig(new TypeConfig("Chance", R.drawable.chance, "#6937B9"));
        addTypeConfig(new TypeConfig("Change", R.drawable.change, "#49A0C4"));
        addTypeConfig(new TypeConfig("Christmas", R.drawable.christmas, "#1F894F"));
        addTypeConfig(new TypeConfig("Communication", R.drawable.communication, "#1D50A3"));
        addTypeConfig(new TypeConfig("Computers", R.drawable.computers, "#1D50A3"));
        addTypeConfig(new TypeConfig("Cool", R.drawable.cool, "#B86613"));
        addTypeConfig(new TypeConfig("Courage", R.drawable.courage, "#D44220"));

        addTypeConfig(new TypeConfig("Dad", R.drawable.dad, "#DD6310"));
        addTypeConfig(new TypeConfig("Dating", R.drawable.dating, "#0064E9"));
        addTypeConfig(new TypeConfig("Death", R.drawable.death, "#464F5D"));
        addTypeConfig(new TypeConfig("Design", R.drawable.design, "#40C140"));
        addTypeConfig(new TypeConfig("Diet", R.drawable.diet, "#1D683E"));
        addTypeConfig(new TypeConfig("Dreams", R.drawable.dreams, "#4500E9"));

        addTypeConfig(new TypeConfig("Easter", R.drawable.easter, "#251B95"));
        addTypeConfig(new TypeConfig("Education", R.drawable.education, "#7EC24D"));
        addTypeConfig(new TypeConfig("Environmental", R.drawable.environmental, "#81A820"));
        addTypeConfig(new TypeConfig("Equality", R.drawable.equality, "#371912"));
        addTypeConfig(new TypeConfig("Experience", R.drawable.experience, "#3D089"));

        addTypeConfig(new TypeConfig("Failure", R.drawable.failure, "#E90086"));
        addTypeConfig(new TypeConfig("Faith", R.drawable.faith, "#370D31"));
        addTypeConfig(new TypeConfig("Family", R.drawable.family, "#6236DD"));
        addTypeConfig(new TypeConfig("Famous", R.drawable.famous, "#EDA022"));
        addTypeConfig(new TypeConfig("Father's Day", R.drawable.father_day, "#296B9C"));
        addTypeConfig(new TypeConfig("Fear", R.drawable.fear, "#300C5B"));
        addTypeConfig(new TypeConfig("Finance", R.drawable.finance, "#238C8E"));
        addTypeConfig(new TypeConfig("Fitness", R.drawable.fitness, "#1C8D21"));

        addTypeConfig(new TypeConfig("Food", R.drawable.food, "#6538E0"));
        addTypeConfig(new TypeConfig("Forgiveness", R.drawable.forgiveness, "#DD303D"));
        addTypeConfig(new TypeConfig("Freedom", R.drawable.freedom, "#A36DD4"));
        addTypeConfig(new TypeConfig("Friendship", R.drawable.friendship, "#17AD93"));

        addTypeConfig(new TypeConfig("Funny", R.drawable.funny, "#49B197"));
        addTypeConfig(new TypeConfig("Future", R.drawable.future, "#7040A1"));

        addTypeConfig(new TypeConfig("God", R.drawable.god, "#DB673B"));
        addTypeConfig(new TypeConfig("Good", R.drawable.good, "#DE651C"));
        addTypeConfig(new TypeConfig("Government", R.drawable.government, "#174C94"));
        addTypeConfig(new TypeConfig("Graduation", R.drawable.graduation, "#1E4272"));
        addTypeConfig(new TypeConfig("Great", R.drawable.great, "#4E5660"));
        addTypeConfig(new TypeConfig("Gardening", R.drawable.gardening, "#4DC25C"));

        addTypeConfig(new TypeConfig("Happiness", R.drawable.happiness, "#DB6F39"));
        addTypeConfig(new TypeConfig("Health", R.drawable.health, "#1E3FC5"));
        addTypeConfig(new TypeConfig("History", R.drawable.history, "#E95868"));
        addTypeConfig(new TypeConfig("Home", R.drawable.home, "#2D5B7D"));
        addTypeConfig(new TypeConfig("Hope", R.drawable.hope, "#69739B"));
        addTypeConfig(new TypeConfig("Humor", R.drawable.humor, "#4AC3ED"));

        addTypeConfig(new TypeConfig("Imagination", R.drawable.imagination, "#2FA8CC"));
        addTypeConfig(new TypeConfig("Independence", R.drawable.independence, "#2A428C"));
        addTypeConfig(new TypeConfig("Inspirational", R.drawable.inspirational, "#CE6A16"));
        addTypeConfig(new TypeConfig("Intelligence", R.drawable.intelligence, "#294B6C"));

        addTypeConfig(new TypeConfig("Jealousy", R.drawable.jealousy, "#3584AD"));


        addTypeConfig(new TypeConfig("Knowledge", R.drawable.knowledge, "#1E8F5B"));

        addTypeConfig(new TypeConfig("Leadership", R.drawable.leadership, "#4366D9"));
        addTypeConfig(new TypeConfig("Learning", R.drawable.learning, "#5380C8"));
        addTypeConfig(new TypeConfig("Legal", R.drawable.legal, "#275EA7"));
        addTypeConfig(new TypeConfig("Life", R.drawable.life, "#138D7A"));
        addTypeConfig(new TypeConfig("Love", R.drawable.love, "#B83641"));

        addTypeConfig(new TypeConfig("Marriage", R.drawable.marriage, "#3E00E9"));
        addTypeConfig(new TypeConfig("Medical", R.drawable.medical, "#1CAC88"));
        addTypeConfig(new TypeConfig("Memorial Day", R.drawable.memorial_day, "#C94434"));
        addTypeConfig(new TypeConfig("Men", R.drawable.men, "#2D6EC4"));
        addTypeConfig(new TypeConfig("Money", R.drawable.money, "#C75903"));
        addTypeConfig(new TypeConfig("Morning", R.drawable.morning, "#008FE9"));
        addTypeConfig(new TypeConfig("Mother's Day", R.drawable.mother_day, "#DB5D82"));
        addTypeConfig(new TypeConfig("Motivational", R.drawable.motivational, "#D23E20"));
        addTypeConfig(new TypeConfig("Movies", R.drawable.movies, "#721423"));
        addTypeConfig(new TypeConfig("Moving On", R.drawable.moving_on, "#0064E9"));
        addTypeConfig(new TypeConfig("Music", R.drawable.music, "#3A00E9"));
        addTypeConfig(new TypeConfig("Mom", R.drawable.mom, "#4500E9"));

        addTypeConfig(new TypeConfig("Nature", R.drawable.nature, "#255D26"));
        addTypeConfig(new TypeConfig("New Year's", R.drawable.new_year, "#7A2121"));

        addTypeConfig(new TypeConfig("Parenting", R.drawable.parenting, "#FB983B"));
        addTypeConfig(new TypeConfig("Patience", R.drawable.patience, "#286987"));
        addTypeConfig(new TypeConfig("Patriotism", R.drawable.patriotism, "#D26A29"));
        addTypeConfig(new TypeConfig("Peace", R.drawable.peace, "#47B07C"));
        addTypeConfig(new TypeConfig("Pet", R.drawable.pet, "#423E4F"));
        addTypeConfig(new TypeConfig("Poetry", R.drawable.poetry, "#E5554E"));
        addTypeConfig(new TypeConfig("Politics", R.drawable.politics, "#B71961"));
        addTypeConfig(new TypeConfig("Positive", R.drawable.positive, "#F78E36"));
        addTypeConfig(new TypeConfig("Power", R.drawable.power, "#5760FF"));

        addTypeConfig(new TypeConfig("Respect", R.drawable.respect, "#35A5D2"));
        addTypeConfig(new TypeConfig("Romantic", R.drawable.romantic, "#FFC7D0"));
        addTypeConfig(new TypeConfig("Relationship", R.drawable.relationship, "#C92A22"));
        addTypeConfig(new TypeConfig("Religion", R.drawable.religion, "#BB5D4C"));


        addTypeConfig(new TypeConfig("Sad", R.drawable.sad, "#4A7D94"));
        addTypeConfig(new TypeConfig("Saint Patrick's Day", R.drawable.saint_patrick_day, "#0A4F0F"));
        addTypeConfig(new TypeConfig("Science", R.drawable.science, "#0064E9"));
        addTypeConfig(new TypeConfig("Society", R.drawable.society, "#7095FF"));
        addTypeConfig(new TypeConfig("Space", R.drawable.space, "#68B5B1"));
        addTypeConfig(new TypeConfig("Strength", R.drawable.strength, "#6F1D30"));
        addTypeConfig(new TypeConfig("Success", R.drawable.success, "#089BAD"));
        addTypeConfig(new TypeConfig("Sympathy", R.drawable.sympathy, "#0064E9"));
        addTypeConfig(new TypeConfig("Smile", R.drawable.smile, "#F78E36"));
        addTypeConfig(new TypeConfig("Sports", R.drawable.sports, "#3584AD"));

        addTypeConfig(new TypeConfig("Teacher", R.drawable.teacher, "#2384C1"));
        addTypeConfig(new TypeConfig("Technology", R.drawable.technology, "#FF9600"));
        addTypeConfig(new TypeConfig("Teen", R.drawable.teen, "#0064E9"));
        addTypeConfig(new TypeConfig("Thankful", R.drawable.thankful, "#FB6A3B"));
        addTypeConfig(new TypeConfig("Thanksgiving", R.drawable.thanks_giving, "#A63A54"));
        addTypeConfig(new TypeConfig("Time", R.drawable.time, "#2B4D66"));
        addTypeConfig(new TypeConfig("Travel", R.drawable.travel, "#FF6969"));
        addTypeConfig(new TypeConfig("Trust", R.drawable.trust, "#0064E9"));
        addTypeConfig(new TypeConfig("Truth", R.drawable.truth, "#507697"));

        addTypeConfig(new TypeConfig("Valentine's Day", R.drawable.valentine_day, "#F88364"));
        addTypeConfig(new TypeConfig("Veterans Day", R.drawable.veterans_day, "#1F9580"));

        addTypeConfig(new TypeConfig("War", R.drawable.war, "#355357"));
        addTypeConfig(new TypeConfig("Wedding", R.drawable.wedding, "#102747"));
        addTypeConfig(new TypeConfig("Wisdom", R.drawable.wisdom, "#BB5D4C"));
        addTypeConfig(new TypeConfig("Women", R.drawable.women, "#F78E36"));
        addTypeConfig(new TypeConfig("Work", R.drawable.work, "#215889"));
    }

    private void addTypeConfig(TypeConfig config) {
        configMap.put(config.name, config);
    }

    public static class TypeConfig {
        public TypeConfig(String name, int iconRes, String color) {
            this.name = name;
            this.iconRes = iconRes;
            this.color = color;
        }

        public String name;
        public int iconRes;
        public String color;
    }
}
