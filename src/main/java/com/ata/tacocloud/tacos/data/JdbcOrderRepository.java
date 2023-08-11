package com.ata.tacocloud.tacos.data;

import com.ata.tacocloud.tacos.Ingredient;
import com.ata.tacocloud.tacos.IngredientRef;
import com.ata.tacocloud.tacos.Taco;
import com.ata.tacocloud.tacos.TacoOrder;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JdbcOrderRepository implements OrderRepository{

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    @Transactional
    public TacoOrder save (TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco_Order"
                + "(delivery_name, delivery_street, delivery_city, delivery_state,"
                + "delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at)"
                + "values (?,?,?,?,?,?,?,?,?)"
                , Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryName(), tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryCity(), tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryZip(), tacoOrder.getCcNumber(),
                        tacoOrder.getCcExpiration(), tacoOrder.getCcCVV(),
                        tacoOrder.getPlacedAt()
                )
        );

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);
        long orderId = generatedKeyHolder.getKey().longValue();
        tacoOrder.setId(orderId);

        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return tacoOrder;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        PreparedStatementCreatorFactory psfc = new PreparedStatementCreatorFactory(
                "insert into Taco" +
                        "(name, created_at, taco_order, taco_order_key)" +
                        "values (?,?,?,?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        );
        psfc.setReturnGeneratedKeys(true);

        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = psfc.newPreparedStatementCreator(
                Arrays.asList(taco.getName(),taco.getCreatedAt(), orderId, orderKey)
        );

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);
        long tacoId = generatedKeyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRefs(tacoId, taco.getIngredients());

        return  tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int key=0;
        for (Ingredient ingredient : ingredients) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, taco, taco_key)"
                    + "values (?,?,?)",
                    ingredient.getName(), tacoId, key++
            );
        }
    }
}
