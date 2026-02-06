package net.sodium.sodiumworld.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
public class JopaEntity extends DisplayEntity {
    public JopaEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }
    private float z_d = 0;
    private int timer = 100;
    private int jopa_state = 0;
    public float SCALEX = 0;
    public float SCALEZ = 0;
    private Vec3d root_pos = new Vec3d(0, 0, 0);
    @Override
    public boolean isPushable() {
        return false;               // ← нельзя толкать
    }
    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    @Override
    protected void refreshData(boolean shouldLerp, float lerpProgress) {

    }

    public void setRootPos(double x, double y, double z){
        this.root_pos = new Vec3d(x, y, z);
    }

    @Override
    public void tick() {
        float speed = 1f;
        float multiplier = 5f;
        if (timer <= 0) {
            if (jopa_state == 0) {
                jopa_state = 1;
                timer = (int) (20 * speed);
            } else if (jopa_state == 1) {
                jopa_state = 3;
                timer = 40;
            } else if (jopa_state == 2) {
                jopa_state = 0;
                timer = 100;
            } else if (jopa_state == 3) {
                jopa_state = 2;
                timer = (int) (20 * speed);
            }
        }
        if (jopa_state == 1) {
            z_d += (1 / (20 * speed))*multiplier;
        } else if (jopa_state == 2) {
            z_d -= (1 / (20 * speed))*multiplier;
        }
        timer--;

        SCALEZ = z_d;
        SCALEX = 1;
        float dist = (float)((0.6-(z_d))/16);
        double yawRadians = Math.toRadians(this.getYaw());
        double offsetX = -dist * Math.sin(yawRadians);
        double offsetZ = dist * Math.cos(yawRadians);
        Vec3d offset = new Vec3d(offsetX, 0, offsetZ);
        this.setPosition(this.root_pos.add(offset));
        timer--;
        super.tick();
    }
}
